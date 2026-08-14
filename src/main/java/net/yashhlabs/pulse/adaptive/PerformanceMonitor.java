package net.yashhlabs.pulse.adaptive;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.yashhlabs.pulse.util.PulseLogger;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Map;

public final class PerformanceMonitor {

	// throttle world sampling
	private static final int WORLD_SAMPLE_INTERVAL = 10;

	private final Deque<Long> frameTimestamps = new ArrayDeque<>();
	private long windowNanos = 1_000_000_000L;
	private int frameCounter = 0;

	private double averageFps = 0;
	private double averageFrameTimeMs = 0;
	private int entityCount = 0;
	private int particleCount = 0;

	private Field particleMapField;
	private boolean particleFieldResolved = false;

	public void setWindowMillis(int windowMillis) {
		this.windowNanos = windowMillis * 1_000_000L;
	}

	public void onFrame() {
		long now = System.nanoTime();
		frameTimestamps.addLast(now);

		long cutoff = now - windowNanos;
		while (!frameTimestamps.isEmpty() && frameTimestamps.peekFirst() < cutoff) {
			frameTimestamps.pollFirst();
		}

		int sampleCount = frameTimestamps.size();
		if (sampleCount >= 2) {
			long spanNanos = frameTimestamps.peekLast() - frameTimestamps.peekFirst();
			double seconds = spanNanos / 1_000_000_000.0;
			if (seconds > 0) {
				averageFps = (sampleCount - 1) / seconds;
				averageFrameTimeMs = (seconds * 1000.0) / (sampleCount - 1);
			}
		}

		frameCounter++;
		if (frameCounter % WORLD_SAMPLE_INTERVAL == 0) {
			sampleWorldCounts();
		}
	}

	private void sampleWorldCounts() {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.world == null) {
			entityCount = 0;
			particleCount = 0;
			return;
		}

		int entities = 0;
		for (Entity ignored : client.world.getEntities()) {
			entities++;
		}
		entityCount = entities;
		particleCount = readParticleCount(client.particleManager);
	}

	// best effort only
	private int readParticleCount(ParticleManager manager) {
		if (manager == null) {
			return 0;
		}

		if (!particleFieldResolved) {
			resolveParticleField(manager);
		}

		if (particleMapField == null) {
			return 0;
		}

		try {
			Object value = particleMapField.get(manager);
			if (!(value instanceof Map<?, ?> map)) {
				return 0;
			}
			int total = 0;
			for (Object bucket : map.values()) {
				if (bucket instanceof Collection<?> collection) {
					total += collection.size();
				}
			}
			return total;
		} catch (IllegalAccessException e) {
			return 0;
		}
	}

	private void resolveParticleField(ParticleManager manager) {
		particleFieldResolved = true;

		try {
			Field field = manager.getClass().getDeclaredField("particles");
			field.setAccessible(true);
			particleMapField = field;
			return;
		} catch (NoSuchFieldException ignored) {
			// name changed maybe
		}

		for (Field field : manager.getClass().getDeclaredFields()) {
			if (Map.class.isAssignableFrom(field.getType())) {
				field.setAccessible(true);
				particleMapField = field;
				return;
			}
		}

		PulseLogger.warn("Pulse could not locate the particle map field, particle count will read 0.");
	}

	public double getAverageFps() {
		return averageFps;
	}

	public double getAverageFrameTimeMs() {
		return averageFrameTimeMs;
	}

	public int getEntityCount() {
		return entityCount;
	}

	public int getParticleCount() {
		return particleCount;
	}
}
