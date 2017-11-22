import ru.coutvv.timeloop.bot.Context;
import ru.coutvv.timeloop.bot.SystemSettings;
import ru.coutvv.timeloop.io.ObservableChat;
import ru.coutvv.timeloop.io.chat.SysoutChat;

import java.time.LocalTime;

/**
 * @author coutvv    20.11.2017
 */
public class TestMorningAlgorithm {

    public static void main(String[] args) {
        ObservableChat chat = new SysoutChat();
        LocalTime time = LocalTime.now().plusSeconds(1);
        SystemSettings settings = new SystemSettings();
        settings.alarmTime = time;
        Context context = new Context(chat, settings);
        context.run();
        chat.closeChat();
    }

}
