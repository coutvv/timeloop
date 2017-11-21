package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.util.WaitUtil;

/**
 * @author coutvv    20.11.2017
 */
public class StateShower extends State {

    private boolean done = false;

    StateShower(Context context) {
        super(context);
    }

    @Override
    public void operation() {
        send("I wait you for shower!! please ask me when you done");
        WaitUtil.waitUntil(() -> done && !skipLever);

        send("Yeah! your day has started now! wakeup body process go to phase #2");
        //TODO: go to phase #2

    }
    @Override
    public void handleMsg(String message) {
        if(message.equals("done"))
            done = true;
    }
}
