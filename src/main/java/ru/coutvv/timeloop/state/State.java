package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.ioservice.ChatObserver;

/**
 * state
 */
public abstract class State implements ChatObserver {

    final Context context;

    State(Context context) {
        this.context = context;
    }

    public abstract void operation();

    @Override
    public void handleEvent(String message) {
        //global messages here!

    }

    protected void send(String message) {
        context.sendMessage(message);
    }
}
