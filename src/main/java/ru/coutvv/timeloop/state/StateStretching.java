package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.util.WaitUtil;

/**
 * @author coutvv    20.11.2017
 */
public class StateStretching extends State{

    private boolean isDone = false;

    private final long STRETCHING_TIMEOUT = 60_000;

    public StateStretching(Context context) {
        super(context);
    }

    @Override
    public void operation() {
        send("just some typical message for wait");
        send("stretch!!!");
        WaitUtil.lagUntil(() -> skipLever, STRETCHING_TIMEOUT);//TODO: stretching list to exercise
        send("end of stretch");

        switchState(new StateShower(getContext()));
    }
    @Override
    public void handleMsg(String message) {



    }
}
