package ru.coutvv.timeloop;

import ru.coutvv.timeloop.ioservice.ObservableChat;
import ru.coutvv.timeloop.state.State;
import ru.coutvv.timeloop.state.StateWait;

import java.time.LocalTime;

/**
 * @author coutvv    20.11.2017
 */
public class Context {
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
       sendMessage("next stage");//debug
       current.operation();
    }

    public void sendMessage(String message) {
        chat.send(message);
    }

    public void run() {
        current.operation();
    }
}
