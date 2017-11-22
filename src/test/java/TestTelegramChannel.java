import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import ru.coutvv.timeloop.io.chat.TelegramBotChannel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author coutvv    22.11.2017
 */
public class TestTelegramChannel {
    private static final Logger logger = Logger.getLogger(TestTelegramChannel.class);
    public static void main(String[] args) throws TelegramApiRequestException {
        TestTelegramChannel test = new TestTelegramChannel();
        String propFileName = "bot.properties";
        InputStream in = test.getClass().getClassLoader().getResourceAsStream(propFileName);
        Properties props = new Properties();
        try {
            props.load(in);
        } catch (IOException e) {
            logger.error("can't load properties file\n" + e.getLocalizedMessage());
        }
        String token = (String) props.get("bot.token");

        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();
        api.registerBot(new TelegramBotChannel("timeloop", token));
        logger.info("bot has registered!");
    }
}
