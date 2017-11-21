import ru.coutvv.timeloop.bot.BotChatWrapper;
import ru.coutvv.timeloop.ioservice.ObservableChat;
import ru.coutvv.timeloop.ioservice.SysoutChat;

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
