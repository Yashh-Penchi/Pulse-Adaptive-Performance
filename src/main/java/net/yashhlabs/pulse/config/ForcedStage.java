package net.yashhlabs.pulse.config;

import net.yashhlabs.pulse.adaptive.AdaptiveStage;

// manual override for testing
public enum ForcedStage {
	AUTO,
	OPTIMAL,
	LIGHT,
	MODERATE,
	AGGRESSIVE;

	public AdaptiveStage toAdaptiveStage() {
		return switch (this) {
			case OPTIMAL -> AdaptiveStage.OPTIMAL;
			case LIGHT -> AdaptiveStage.LIGHT;
			case MODERATE -> AdaptiveStage.MODERATE;
			case AGGRESSIVE -> AdaptiveStage.AGGRESSIVE;
			case AUTO -> AdaptiveStage.OPTIMAL;
		};
	}
}
