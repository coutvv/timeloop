package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.bot.Context;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BooleanSupplier;

/**
 * @author coutvv    20.11.2017
 */
public class StateWait extends State {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final LocalTime startTime;
    private BooleanSupplier itsTime;

    public StateWait(Context context) {
        super(context);
        startTime = context.getSettings().alarmTime;

        itsTime = () -> LocalTime.now().isAfter(startTime);
        if(startTime.isBefore(LocalTime.now())) {
            LocalDateTime date = LocalDateTime.now().plusDays(1);
            date.with(startTime);
            itsTime = () -> date.isBefore(LocalDateTime.now());
        }
    }

    @Override
    public void operation() {
        send("Alarm clock set at " + startTime.format(FORMATTER));
        lag.until(itsTime);
        send("Good Morning! I wish you a good day! Let's start it with me!");
        lag.until(1000);

        switchState(new StateTerror(getContext()));
    }

    @Override
    public void handleMsg(String message) {
        send("We wait alarm clock at " + startTime.format(FORMATTER));
    }
}
