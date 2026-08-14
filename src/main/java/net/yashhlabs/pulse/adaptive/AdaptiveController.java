package net.yashhlabs.pulse.adaptive;

import net.yashhlabs.pulse.config.ForcedStage;
import net.yashhlabs.pulse.config.PulseConfig;
import net.yashhlabs.pulse.util.PulseLogger;

// nothing applied yet
public final class AdaptiveController {

	private final PerformanceMonitor monitor;

	private AdaptiveStage currentStage = AdaptiveStage.OPTIMAL;
	private StageSettings desiredSettings = new StageSettings(16, 12, 100, 100);
	private long lastTransitionNanos = 0;

	public AdaptiveController(PerformanceMonitor monitor) {
		this.monitor = monitor;
	}

	public void tick(PulseConfig config) {
		if (!config.enabled || !config.adaptiveEnabled) {
			setStage(AdaptiveStage.OPTIMAL, config, true);
			return;
		}

		if (config.forcedStage != ForcedStage.AUTO) {
			setStage(config.forcedStage.toAdaptiveStage(), config, true);
			return;
		}

		AdaptiveStage desired = decideStage(config);
		long cooldownNanos = config.cooldownSeconds * 1_000_000_000L;
		boolean cooldownOver = System.nanoTime() - lastTransitionNanos >= cooldownNanos;

		if (desired != currentStage && cooldownOver) {
			setStage(desired, config, false);
		}
	}

	private AdaptiveStage decideStage(PulseConfig config) {
		double fps = monitor.getAverageFps();
		if (fps <= 0) {
			return currentStage;
		}

		double ratio = fps / config.targetFps;

		AdaptiveStage rawTarget;
		if (ratio < config.aggressiveThresholdPercent / 100.0) {
			rawTarget = AdaptiveStage.AGGRESSIVE;
		} else if (ratio < config.moderateThresholdPercent / 100.0) {
			rawTarget = AdaptiveStage.MODERATE;
		} else if (ratio < config.lightThresholdPercent / 100.0) {
			rawTarget = AdaptiveStage.LIGHT;
		} else {
			rawTarget = AdaptiveStage.OPTIMAL;
		}

		// need real recovery first
		boolean easingUp = rawTarget.ordinal() < currentStage.ordinal();
		if (easingUp && ratio < config.recoveryThresholdPercent / 100.0) {
			return currentStage;
		}

		return rawTarget;
	}

	private void setStage(AdaptiveStage stage, PulseConfig config, boolean skipCooldown) {
		boolean changed = stage != currentStage;
		currentStage = stage;
		desiredSettings = computeSettings(stage, config);

		if (changed) {
			if (!skipCooldown) {
				lastTransitionNanos = System.nanoTime();
			}
			if (config.logStageChanges) {
				PulseLogger.info("Pulse stage -> " + stage);
			}
		}
	}

	private StageSettings computeSettings(AdaptiveStage stage, PulseConfig config) {
		return switch (stage) {
			case OPTIMAL -> new StageSettings(config.maxRenderDistance, config.maxSimulationDistance, 100, 100);
			case LIGHT -> new StageSettings(
					lerp(config.maxRenderDistance, config.minRenderDistance, 0.33),
					lerp(config.maxSimulationDistance, config.minSimulationDistance, 0.33),
					config.lightEntityDistancePercent,
					config.lightParticleMultiplierPercent);
			case MODERATE -> new StageSettings(
					lerp(config.maxRenderDistance, config.minRenderDistance, 0.66),
					lerp(config.maxSimulationDistance, config.minSimulationDistance, 0.66),
					config.moderateEntityDistancePercent,
					config.moderateParticleMultiplierPercent);
			case AGGRESSIVE -> new StageSettings(
					config.minRenderDistance,
					config.minSimulationDistance,
					config.aggressiveEntityDistancePercent,
					config.aggressiveParticleMultiplierPercent);
		};
	}

	private int lerp(int max, int min, double t) {
		return (int) Math.round(max - (max - min) * t);
	}

	public AdaptiveStage getCurrentStage() {
		return currentStage;
	}

	public StageSettings getDesiredSettings() {
		return desiredSettings;
	}
}
