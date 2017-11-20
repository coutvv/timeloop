package ru.coutvv.timeloop;

import ru.coutvv.timeloop.state.State;

/**
 * @author coutvv    20.11.2017
 */
public class Context {
    private State current;

    public void setState(final State state) {
       this.current = state;
       state.operation();
    }
}
