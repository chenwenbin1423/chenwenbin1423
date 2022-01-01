package com.touzitop.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class CustomLogger {

    static Logger infofilelogger = LoggerFactory.getLogger("custominfologger");
    static Logger debugfilelogger = LoggerFactory.getLogger("customdebuglogger");
    static Logger testnamereport = LoggerFactory.getLogger("testreport");
    static final Logger consolelogger = LoggerFactory.getLogger(CustomLogger.class);

    private static final String debugString = " ~~ %s ~ Class: %s ~ Method: %s ~ %s";
    private static final String infoString = " ~~%s ~ %s ~ %s";
    private static final String errorString = " ~~ %s ~ %s";
    private static final String testReportString = " ~~ Scenario: %s ~ Tags: %s ~ Elapsed Time: %s ms";
    private static String print;

    public static void publish(Level level, String classname, String methodname, String status, String message) {

        if (level.equals(Level.DEBUG)) {
            print = String.format(debugString, level, classname, methodname, methodname);
            debugfilelogger.debug(print);
            consolelogger.debug(System.lineSeparator() + print);
        } else if (level.equals(Level.INFO)) {
            print = String.format(infoString, level, status, message);
            infofilelogger.info(print);
            consolelogger.info(print);
        } else if (level.equals(Level.ERROR)) {
            print = String.format(errorString, classname, message);
            infofilelogger.error(print);
            debugfilelogger.error(print);
            consolelogger.error(print);
        }
    }

    public static void publishTestsExecuted(String scenario, Collection<String> tags, long elapsedTime) {
        print = String.format(testReportString, scenario, tags, elapsedTime);
        testnamereport.info(print);
    }
}

enum Level {
    DEBUG("DEBUG"),
    INFO("INFO"),
    ERROR("ERROR");

    private final String value;

    private Level(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
