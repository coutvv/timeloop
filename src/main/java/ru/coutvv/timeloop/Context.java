package ru.coutvv.timeloop;

import org.apache.log4j.Logger;
import ru.coutvv.timeloop.ioservice.ObservableChat;
import ru.coutvv.timeloop.state.State;
import ru.coutvv.timeloop.state.StateWait;

import java.time.LocalTime;

/**
 * @author coutvv    20.11.2017
 */
public class Context {
    private static final Logger logger = Logger.getLogger(Context.class);
    private State current;
    private ObservableChat chat;

    public Context(ObservableChat chat, LocalTime time) {
        current = new StateWait(this, time);
        this.chat = chat;
        chat.setObserver(current);
    }

    public void setState(final State state) {
       current = state;
       chat.setObserver(state);
       current.operation();

       logger.info("next state: " + state.getClass().getSimpleName());
    }

    public void sendMessage(String message) {
        chat.send(message);
    }

    public void run() {
        current.operation();
    }
}
