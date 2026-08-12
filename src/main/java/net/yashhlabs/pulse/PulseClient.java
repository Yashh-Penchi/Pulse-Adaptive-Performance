package net.yashhlabs.pulse;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.yashhlabs.pulse.config.PulseConfig;
import net.yashhlabs.pulse.keybind.PulseKeybinds;
import net.yashhlabs.pulse.util.PulseLogger;

/**
 * Client entrypoint for Pulse Adaptive Performance.
 * This is a foundation build: it wires up config, keybindings, and
 * logging only — no adaptive/performance logic lives here yet.
 */
public class PulseClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		AutoConfig.register(PulseConfig.class, GsonConfigSerializer::new);
		PulseKeybinds.register();

		PulseLogger.info("Pulse Adaptive Performance initialized (foundation build, no performance features active).");
	}
}
