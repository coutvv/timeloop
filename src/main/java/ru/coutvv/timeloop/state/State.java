package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.ioservice.ChatObserver;

/**
 * state
 */
public abstract class State implements ChatObserver {

    private final Context context;
    /**
     * use to stop progress in states and exit
     */
    protected boolean skipLever = false;

    State(Context context) {
        this.context = context;
    }

    public abstract void operation();

    @Override
    public final void handleEvent(String message) {
        //global messages here!
        if(message.equals("/stop")) {
           skipLever = true;
        } if(message.equals("/help")) {

        } if(message.equals("/settings")) {

        } else {
            handleMsg(message);
        }

    }

    public Context getContext() {
       return context;
    }

    public abstract void handleMsg(String message);

    protected void send(String message) {
        if(skipLever) return;
        context.sendMessage(message);
    }

    protected void switchState(State next) {
        if(skipLever) return;
        context.setState(next);
    }
}
