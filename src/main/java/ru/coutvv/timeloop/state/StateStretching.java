package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.ioservice.ObservableChat;
import ru.coutvv.timeloop.util.WaitUtil;

/**
 * @author coutvv    20.11.2017
 */
public class StateStretching extends State{

    private boolean isDone = false;

    private final long STRETCHING_TIMEOUT = 60_000;

    public StateStretching(Context context, ObservableChat chat) {
        super(context, chat);
    }

    @Override
    public void operation() {
        chat.send("just some typical message for wait");
        chat.send("stretch!!!");
        WaitUtil.lag(STRETCHING_TIMEOUT);
        chat.send("end of stretch");

        State next = new StateShower(context, chat);
        chat.setObserver(next);
        context.setState(next);
    }
    @Override
    public void handleEvent(String message) {


    }
}
