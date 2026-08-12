package net.yashhlabs.pulse;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.yashhlabs.pulse.adaptive.AdaptiveController;
import net.yashhlabs.pulse.adaptive.PerformanceMonitor;
import net.yashhlabs.pulse.config.PulseConfig;
import net.yashhlabs.pulse.keybind.PulseKeybinds;
import net.yashhlabs.pulse.util.PulseLogger;

public class PulseClient implements ClientModInitializer {

	public static PerformanceMonitor MONITOR;
	public static AdaptiveController CONTROLLER;

	@Override
	public void onInitializeClient() {
		AutoConfig.register(PulseConfig.class, GsonConfigSerializer::new);
		PulseKeybinds.register();

		MONITOR = new PerformanceMonitor();
		CONTROLLER = new AdaptiveController(MONITOR);

		LevelRenderEvents.END_MAIN.register(context -> {
			PulseConfig config = AutoConfig.getConfigHolder(PulseConfig.class).getConfig();
			MONITOR.setWindowMillis(config.rollingWindowMs);
			MONITOR.onFrame();
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			PulseConfig config = AutoConfig.getConfigHolder(PulseConfig.class).getConfig();
			CONTROLLER.tick(config);
		});

		PulseLogger.info("Pulse Adaptive Performance initialized. Monitoring only, nothing applied yet.");
	}
}
