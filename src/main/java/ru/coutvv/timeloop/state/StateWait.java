package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.ioservice.ObservableChat;

import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

/**
 * @author coutvv    20.11.2017
 */
public class StateWait extends State {

    private final LocalTime startTime;

    public StateWait(Context context, ObservableChat chat, LocalTime time) {
        super(context, chat);
        startTime = time;
    }


    @Override
    public void operation() {
        LocalTime time = LocalTime.now();
        chat.send("wait for");
        while(time.isBefore(startTime)) {
            Thread.yield();
            time = LocalTime.now();
        }
        State terror = new StateTerror(context, chat);
        chat.setObserver(terror);
        context.setState(terror);
    }

    @Override
    public void handleEvent(String message) {
        chat.send(message);
    }
}
