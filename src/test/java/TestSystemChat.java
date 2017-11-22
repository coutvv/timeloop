import ru.coutvv.timeloop.bot.BotChatWrapper;
import ru.coutvv.timeloop.io.ObservableChat;
import ru.coutvv.timeloop.io.chat.SysoutChat;

/**
 * test chat with bot commands
 *
 * @author coutvv    21.11.2017
 */
public class TestSystemChat {

    public static void main(String[] args) {
        ObservableChat chat = new SysoutChat();
        new BotChatWrapper(chat);
    }
}
