import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.ioservice.ObservableChat;
import ru.coutvv.timeloop.ioservice.SysoutChat;
import ru.coutvv.timeloop.state.State;
import ru.coutvv.timeloop.state.StateWait;

import java.time.LocalTime;

/**
 * @author coutvv    20.11.2017
 */
public class TestMorningAlgorithm {

    public static void main(String[] args) {
        Context context = new Context();
        ObservableChat chat = new SysoutChat();
        State wait = new StateWait(context, chat, LocalTime.of(3, 32));
        chat.setObserver(wait);
        context.setState(wait);
    }

}
