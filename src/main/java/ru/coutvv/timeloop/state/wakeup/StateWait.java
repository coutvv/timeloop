package ru.coutvv.timeloop.state.wakeup;

import ru.coutvv.timeloop.bot.Context;
import ru.coutvv.timeloop.state.State;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BooleanSupplier;

import static ru.coutvv.timeloop.bot.setting.ConstConfig.*;

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
        String template = props().txt(WAIT_SET_ALARM_TEMPLATE);
        send(String.format(template, startTime.format(FORMATTER)));
        lag.until(itsTime);
        send(WAIT_AWAKE_MESSAGE);
        lag.until(1000);

        switchState(new StateTerror(getContext()));
    }

    @Override
    public void handleMsg(String message) {
        String template = props().txt(WAIT_HANDLE_TEMPLATE);
        send(String.format(template, startTime.format(FORMATTER)));
    }
}
