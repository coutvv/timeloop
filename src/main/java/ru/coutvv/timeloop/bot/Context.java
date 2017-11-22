package ru.coutvv.timeloop.bot;

import org.apache.log4j.Logger;
import ru.coutvv.timeloop.bot.setting.SystemSettings;
import ru.coutvv.timeloop.io.ObservableChat;
import ru.coutvv.timeloop.state.State;
import ru.coutvv.timeloop.state.wakeup.StateWait;

/**
 * Runnable context of alarming
 *
 * @author coutvv    20.11.2017
 */
public class Context {
    private static final Logger logger = Logger.getLogger(Context.class);
    private State current;
    private ObservableChat chat;
    private SystemSettings settings;

    public Context(ObservableChat chat, SystemSettings settings) {
        this.settings = settings;
        this.chat = chat;

        current = new StateWait(this);
        chat.addObserver(current);
    }

    public SystemSettings getSettings() {
        return settings;
    }

    public void setState(final State state) {
       chat.removeObserver(current);
       chat.addObserver(state);

       current = state;
       current.operation();

       logger.info("next state: " + state.getClass().getSimpleName());
    }

    public void sendMessage(String message) {
        chat.send(message);
    }

    public void run() {
        Thread thread = new Thread(() -> current.operation());
        thread.start();
    }

    public void kill() {
        chat.removeObserver(current);
        current.stop();
    }
}
