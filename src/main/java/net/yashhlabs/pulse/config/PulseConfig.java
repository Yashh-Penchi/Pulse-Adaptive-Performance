package net.yashhlabs.pulse.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

/**
 * Client-side config for Pulse. This is a bare foundation — no adaptive
 * performance behaviour is implemented yet, just the toggle that future
 * systems will read.
 */
@Config(name = "pulse")
public class PulseConfig implements ConfigData {

	@ConfigEntry.Gui.Tooltip
	public boolean enabled = true;
}
