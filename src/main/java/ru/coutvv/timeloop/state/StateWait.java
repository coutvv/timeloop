package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.util.WaitUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.BooleanSupplier;

/**
 * @author coutvv    20.11.2017
 */
public class StateWait extends State {

    private final LocalTime startTime;

    private BooleanSupplier itsTime;

    public StateWait(Context context, LocalTime time) {
        super(context);
        startTime = time;

        itsTime = () -> LocalTime.now().isAfter(startTime);
        if(startTime.isBefore(LocalTime.now())) {
            LocalDateTime date = LocalDateTime.now().plusDays(1);
            date.with(startTime);
            itsTime = () -> date.isBefore(LocalDateTime.now());
        }
    }

    @Override
    public void operation() {
        send("wait for");
        WaitUtil.waitUntil(itsTime);

        State terror = new StateTerror(context);
        context.setState(terror);
    }

    private final String WAIT_ANSWER = "we wait for alarm clock";

    @Override
    public void handleEvent(String message) {
        send(WAIT_ANSWER);
    }
}
