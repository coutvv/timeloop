package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        send("Alarm clock set at " + startTime.format(DateTimeFormatter.ofPattern("hh:mm")));
        lag.until(itsTime);
        send("Good Morning! I wish you a good day! Let's start it with me!");
        lag.until(1000);

        switchState(new StateTerror(getContext()));
    }

    @Override
    public void handleMsg(String message) {
        send("We wait alarm clock at " + startTime.format(DateTimeFormatter.ofPattern("hh:mm")));
    }
}
