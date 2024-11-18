package com.fileorganizer;

import org.apache.log4j.Logger;

public class LoggerUtil {

    private static final Logger logger = Logger.getLogger(LoggerUtil.class);

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message, Exception e) {
        logger.error(message, e);
    }
}
