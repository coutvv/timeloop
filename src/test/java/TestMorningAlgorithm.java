import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.ioservice.ObservableChat;
import ru.coutvv.timeloop.ioservice.SysoutChat;

import java.time.LocalTime;

/**
 * @author coutvv    20.11.2017
 */
public class TestMorningAlgorithm {

    public static void main(String[] args) {
        ObservableChat chat = new SysoutChat();
        LocalTime time = LocalTime.now().plusSeconds(1);
        Context context = new Context(chat, time);
        context.run();
        chat.closeChat();
    }

}
