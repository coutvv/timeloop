package ru.coutvv.timeloop.bot.setting;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author coutvv    23.11.2017
 */
public class ConfigProperties extends Properties {
    private static final Logger logger = Logger.getLogger(ConfigProperties.class);

    public ConfigProperties(String propsFilename) {
        super();

        InputStream in = this.getClass().getClassLoader().getResourceAsStream(propsFilename);
        try {
            this.load(in);
        } catch (IOException e) {
            logger.error("can't load properties file\n" + e.getLocalizedMessage());
        }
    }

    @Override
    public Object get(Object key) {
        return super.get(key.toString());
    }

    public String txt(Object key) {
        String result = "ERROR: decoding problem: \n";
        final String encodedString = (String) this.get(key);
        try {
            result = new String(encodedString.getBytes("ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            logger.error("can't decode message : " + encodedString + "\n" + e.getLocalizedMessage());
            result += encodedString;
        }
        return result;
    }

    public Long lNum(Object key) {
        return Long.parseLong(txt(key));
    }

    public Integer iNum(Object key) {
        return Integer.parseInt(txt(key));
    }
}
