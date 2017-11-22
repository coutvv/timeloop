import ru.coutvv.timeloop.io.ChatObserver;
import ru.coutvv.timeloop.io.ObservableChat;
import ru.coutvv.timeloop.io.chat.SysoutChat;

/**
 * test console chat
 *
 * @author coutvv    20.11.2017
 */
public class TestObservableChat {

    public static void main(String[] args) throws InterruptedException {

        ObservableChat sysChat = new SysoutChat();
        ChatObserver observer = (message) -> System.out.println("obs get message: " + message);

        sysChat.addObserver(observer);
        Runnable runnable = () -> {
            try {
                Thread.sleep(2000);
                sysChat.send("message 01");
                Thread.sleep(5000);
                sysChat.send("message 02");
                Thread.sleep(2000);
                sysChat.send("last message");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
        Thread.sleep(10000);
        sysChat.closeChat();
        System.out.println("Shutdown");
    }

}
