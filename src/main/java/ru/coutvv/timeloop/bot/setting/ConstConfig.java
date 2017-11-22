package ru.coutvv.timeloop.bot.setting;

public enum ConstConfig {
    /** state Waite */
    WAIT_SET_ALARM_TEMPLATE, WAIT_AWAKE_MESSAGE, WAIT_HANDLE_TEMPLATE,
    /** state terror */
    TERROR_WAKEUP, TERROR_WARNING, TERROR_PRESENT_KEY_TEMPLATE,
    TERROR_HANDLE_ACCEPT, TERROR_HANDLE_ACCEPT_2,
    TERROR_HANDLE_DENY, TERROR_KEY_SIZE, TERROR_KEY_TIMEOUT,
    TERROR_KEY_SIGNS
    ;

    @Override
    public String toString() {
        return this.name();
    }
}
