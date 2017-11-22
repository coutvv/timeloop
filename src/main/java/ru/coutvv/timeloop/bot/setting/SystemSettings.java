package ru.coutvv.timeloop.bot.setting;

import org.apache.log4j.Logger;

import java.time.LocalTime;

/**
 * @author coutvv    21.11.2017
 */
public class SystemSettings {
    private static final Logger logger = Logger.getLogger(SystemSettings.class);

    public final LocalTime DEFAULT_ALARM_TIME = LocalTime.of(5, 50);
    public LocalTime alarmTime = DEFAULT_ALARM_TIME;
    private ConfigProperties properties = new ConfigProperties("app.properties");

    public ConfigProperties getProperties() {
        return properties;
    }

    public void setProperties(String propsFilename) {
        properties = new ConfigProperties(propsFilename);
    }
}
