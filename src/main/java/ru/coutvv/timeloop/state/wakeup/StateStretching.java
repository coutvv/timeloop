package ru.coutvv.timeloop.state.wakeup;

import ru.coutvv.timeloop.bot.Context;
import ru.coutvv.timeloop.state.State;

/**
 * @author coutvv    20.11.2017
 */
public class StateStretching extends State {

    private boolean isDone = false;

    private final long STRETCHING_TIMEOUT = 60_00;

    public StateStretching(Context context) {
        super(context);
    }

    @Override
    public void operation() {
        send("just some typical message for wait");
        send("stretch!!!");
        lag.until(STRETCHING_TIMEOUT);//TODO: stretching list to exercise
        send("end of stretch");

        switchState(new StateShower(getContext()));
    }
    @Override
    public void handleMsg(String message) {



    }
}
