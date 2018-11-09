package com.ynov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        Logger logger_root = LogManager.getRootLogger();

        logger.debug("debug");
        logger.error("error");
        logger.warn("warning");
        logger.info("info");
        logger.fatal("fatal");

    }
}
