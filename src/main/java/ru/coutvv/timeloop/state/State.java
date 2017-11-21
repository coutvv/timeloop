package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.ioservice.ChatObserver;

/**
 * state
 */
public abstract class State implements ChatObserver {

    final Context context;
    protected boolean skip = false;

    State(Context context) {
        this.context = context;
    }

    public abstract void operation();

    @Override
    public final void handleEvent(String message) {
        //global messages here!
        if(message.equals("/skip")) {
           skip = true;
        } if(message.equals("/help")) {

        } if(message.equals("/settings")) {

        } else {
            handleMsg(message);
        }

    }

    public abstract void handleMsg(String message);

    protected void send(String message) {
        context.sendMessage(message);
    }
}
