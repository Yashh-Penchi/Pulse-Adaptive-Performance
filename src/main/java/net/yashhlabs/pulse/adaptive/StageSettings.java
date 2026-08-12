package net.yashhlabs.pulse.adaptive;

// desired values, nothing applies these yet
public record StageSettings(
		int renderDistance,
		int simulationDistance,
		int entityDistancePercent,
		int particleMultiplierPercent
) {
}
