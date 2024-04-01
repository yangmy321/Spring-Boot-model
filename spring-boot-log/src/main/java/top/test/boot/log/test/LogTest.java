package top.test.boot.log.test;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Slf4j
public class LogTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
//        LOGGER.trace("Trace");
//        LOGGER.debug("Debug");
//        LOGGER.info("Info");
//        LOGGER.warn("Warn");
//        LOGGER.error("Error");
//        log.info("hello world");
        Logger myLogger = LoggerFactory.getLogger("myLogger");
        myLogger.info("hello world");


    }
}
