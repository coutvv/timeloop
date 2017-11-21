package ru.coutvv.timeloop.util;

import org.apache.log4j.Logger;

import java.util.function.BooleanSupplier;

/**
 * @author coutvv    20.11.2017
 */
public class WaitUtil {
    private final static Logger logger = Logger.getLogger(WaitUtil.class);

    private static long TIME_SLICE = 10;

    public static void lag(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            logger.error("can't lag:" + e.getLocalizedMessage());
        }
    }

    public static void waitUntil(BooleanSupplier itsTime) {
        while(!itsTime.getAsBoolean()) {
            Thread.yield();
        }
    }
    public static void lagUntil(BooleanSupplier itsTime, long msTimeout) {
        long timeout = 0;
        while(!itsTime.getAsBoolean() && timeout < msTimeout) {
           timeout += TIME_SLICE;
           lag(TIME_SLICE);
        }
    }
}
