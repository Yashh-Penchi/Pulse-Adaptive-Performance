package net.yashhlabs.pulse.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// shared logger tag
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
