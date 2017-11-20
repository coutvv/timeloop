package ru.coutvv.timeloop.util;

/**
 * @author coutvv    20.11.2017
 */
public class WaitUtil {

    public static void lag(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
