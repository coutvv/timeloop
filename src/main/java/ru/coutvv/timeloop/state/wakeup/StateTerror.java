package ru.coutvv.timeloop.state.wakeup;

import ru.coutvv.timeloop.bot.Context;
import ru.coutvv.timeloop.state.State;

import java.util.Random;

import static ru.coutvv.timeloop.bot.setting.ConstConfig.*;

/**
 * @author coutvv    20.11.2017
 */
public class StateTerror extends State {

    private boolean isAwake = false;

    private String key;

    public StateTerror(Context context) {
        super(context);
    }

    @Override
    public void operation() {
        long timeout = props().lNum(TERROR_KEY_TIMEOUT);
        lag.repeatAfter(() -> isAwake, timeout, () -> {
            key = generateKey();
            send(TERROR_WAKEUP);
            send(TERROR_WARNING);
            String template = props().txt(TERROR_PRESENT_KEY_TEMPLATE);
            send(String.format(template, key));
        });

        switchState(new StateStretching(getContext()));
    }

    @Override
    public void handleMsg(String message) {
        if(message.equals(key)){
            send(TERROR_HANDLE_ACCEPT);
            send(TERROR_HANDLE_ACCEPT_2);
            isAwake = true;
        } else {
            send(props().txt(TERROR_HANDLE_DENY));
        }

    }

    private String generateKey() {
        Random rand = new Random();
        String alphabet = props().txt(TERROR_KEY_SIGNS);
        char[] key = new char[props().iNum(TERROR_KEY_SIZE)];
        for(int i = 0; i < key.length; i++) {
            key[i] = alphabet.charAt(rand.nextInt(alphabet.length()));
        }
        return new String(key);
    }
}
