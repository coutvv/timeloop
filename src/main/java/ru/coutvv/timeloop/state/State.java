package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.ioservice.ChatObserver;
import ru.coutvv.timeloop.ioservice.ObservableChat;

/**
 * state
 */
public abstract class State implements ChatObserver {

    final Context context;
    final ObservableChat chat;

    State(Context context, ObservableChat chat) {
        this.context = context;
        this.chat = chat;
    }

    public abstract void operation();

}
