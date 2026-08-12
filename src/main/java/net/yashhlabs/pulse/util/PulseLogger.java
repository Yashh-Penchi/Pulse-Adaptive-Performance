package net.yashhlabs.pulse.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thin wrapper around the mod's SLF4J logger so every class logs
 * under the same "Pulse" tag instead of importing SLF4J everywhere.
 */
public final class PulseLogger {

	private static final Logger LOGGER = LoggerFactory.getLogger("Pulse");

	private PulseLogger() {
	}

	public static void info(String message) {
		LOGGER.info(message);
	}

	public static void warn(String message) {
		LOGGER.warn(message);
	}

	public static void error(String message) {
		LOGGER.error(message);
	}

	public static void error(String message, Throwable throwable) {
		LOGGER.error(message, throwable);
	}
}
