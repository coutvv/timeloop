package ru.coutvv.timeloop.state;

import org.apache.log4j.Logger;
import ru.coutvv.timeloop.bot.Context;
import ru.coutvv.timeloop.io.ChatObserver;
import ru.coutvv.timeloop.bot.setting.ConfigProperties;
import ru.coutvv.timeloop.bot.setting.ConstConfig;

import java.util.function.BooleanSupplier;

import static ru.coutvv.timeloop.util.WaitUtil.lag;

/**
 * state
 */
public abstract class State implements ChatObserver {
    private static final Logger logger = Logger.getLogger(State.class);

    private final Context context;
    /**
     * use to stop progress in states and exit
     */
    private boolean skipLever = false;

    protected State(Context context) {
        this.context = context;
    }

    public void stop() {
        skipLever = true;
    }

    public abstract void operation();

    @Override
    public final void handleEvent(String message) {
        //global messages here!
        if(message != null) {
            handleMsg(message);
        }
    }

    protected ConfigProperties props() {
        return context.getSettings().getProperties();
    }

    protected Context getContext() {
       return context;
    }

    protected abstract void handleMsg(String message);

    protected void send(String message) {
        if(skipLever) return;
        context.sendMessage(message);
        lag.until(500);//delay after send message
    }

    protected void send(ConstConfig temp) {
        send(props().txt(temp));
    }

    protected void switchState(State next) {
        if(skipLever) return;
        context.setState(next);
    }

    protected Lag lag = new Lag();

    protected class Lag {
        private int TIME_SLICE = 10;
        public void until(BooleanSupplier itsTime) {
            while(!itsTime.getAsBoolean() && !skipLever) {
                Thread.yield();
            }
        }
        public void until(long ms) {
            until(() ->false, ms);
        }
        public void until(BooleanSupplier itsTime, long msTimeout) {
            long timeout = 0;
            while(!itsTime.getAsBoolean() && timeout < msTimeout && !skipLever) {
                timeout += TIME_SLICE;
                lag(TIME_SLICE);
            }
        }
        public void repeatAfter(BooleanSupplier exitCondition, long msTimeout, Runnable action) {
            while(!skipLever && !exitCondition.getAsBoolean()) {
                action.run();
                until(exitCondition, msTimeout);
            }
        }
    }
}
