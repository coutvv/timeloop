import ru.coutvv.timeloop.io.ChatObserver;
import ru.coutvv.timeloop.io.ObservableChat;
import ru.coutvv.timeloop.io.chat.SysoutChat;

/**
 * @author coutvv    21.11.2017
 */
public class TestSoutChat {
    public static void main(String[] args) {
        ObservableChat sysChat = new SysoutChat();
        ChatObserver observer = (message) -> System.out.println("obs get message: " + message);

        sysChat.addObserver(observer);

        System.out.println("after chat");
        sysChat.closeChat();
        System.out.println("close chat");
    }
}
