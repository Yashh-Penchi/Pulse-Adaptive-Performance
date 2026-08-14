package net.yashhlabs.pulse.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "pulse")
public class PulseConfig implements ConfigData {

	// general

	@ConfigEntry.Category("general")
	@ConfigEntry.Gui.Tooltip
	public boolean enabled = true;

	@ConfigEntry.Category("general")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 300, min = 30)
	public int targetFps = 120;

	@ConfigEntry.Category("general")
	@ConfigEntry.Gui.Tooltip
	public boolean logStageChanges = true;

	// adaptive settings

	@ConfigEntry.Category("adaptive")
	@ConfigEntry.Gui.Tooltip
	public boolean adaptiveEnabled = true;

	@ConfigEntry.Category("adaptive")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 10, min = 1)
	public int cooldownSeconds = 3;

	@ConfigEntry.Category("adaptive")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 32, min = 2)
	public int minRenderDistance = 6;

	@ConfigEntry.Category("adaptive")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 32, min = 2)
	public int maxRenderDistance = 16;

	@ConfigEntry.Category("adaptive")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 32, min = 2)
	public int minSimulationDistance = 5;

	@ConfigEntry.Category("adaptive")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 32, min = 2)
	public int maxSimulationDistance = 12;

	// stage thresholds

	@ConfigEntry.Category("thresholds")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 99, min = 50)
	public int lightThresholdPercent = 90;

	@ConfigEntry.Category("thresholds")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 89, min = 30)
	public int moderateThresholdPercent = 75;

	@ConfigEntry.Category("thresholds")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 79, min = 10)
	public int aggressiveThresholdPercent = 50;

	@ConfigEntry.Category("thresholds")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 150, min = 90)
	public int recoveryThresholdPercent = 105;

	// advanced

	@ConfigEntry.Category("advanced")
	@ConfigEntry.Gui.Tooltip
	public ForcedStage forcedStage = ForcedStage.AUTO;

	@ConfigEntry.Category("advanced")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 3000, min = 250)
	public int rollingWindowMs = 1000;

	@ConfigEntry.Category("advanced")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 100, min = 10)
	public int lightParticleMultiplierPercent = 90;

	@ConfigEntry.Category("advanced")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 100, min = 10)
	public int moderateParticleMultiplierPercent = 60;

	@ConfigEntry.Category("advanced")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 100, min = 10)
	public int aggressiveParticleMultiplierPercent = 30;

	@ConfigEntry.Category("advanced")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 100, min = 10)
	public int lightEntityDistancePercent = 90;

	@ConfigEntry.Category("advanced")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 100, min = 10)
	public int moderateEntityDistancePercent = 65;

	@ConfigEntry.Category("advanced")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(max = 100, min = 10)
	public int aggressiveEntityDistancePercent = 40;
}
