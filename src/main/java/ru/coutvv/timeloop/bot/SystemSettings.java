package ru.coutvv.timeloop.bot;

import java.time.LocalTime;

/**
 * @author coutvv    21.11.2017
 */
public class SystemSettings {
    public final LocalTime DEFAULT_ALARM_TIME = LocalTime.of(5, 50);
    public LocalTime alarmTime = DEFAULT_ALARM_TIME;

}
