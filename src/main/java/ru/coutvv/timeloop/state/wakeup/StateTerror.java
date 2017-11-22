package ru.coutvv.timeloop.state.wakeup;

import ru.coutvv.timeloop.bot.Context;
import ru.coutvv.timeloop.state.State;

import java.util.Random;

/**
 * @author coutvv    20.11.2017
 */
public class StateTerror extends State {

    private boolean isAwake = false;

    private final int KEY_SIZE = 20;
    private final int RESET_KEY_TIMEOUT = 60_000;
    private String key;

    public StateTerror(Context context) {
        super(context);
    }

    @Override
    public void operation() {
        lag.repeatAfter(() -> isAwake, RESET_KEY_TIMEOUT, () -> {
            key = generateKey();
            send("It's time to WAKEUP!");
            send("Key is:\n" + key);
            send("After minute it'll be reset");
        });

        switchState(new StateStretching(getContext()));
    }

    @Override
    public void handleMsg(String message) {
        if(message.equals(key)){
            send("Key confirmed");
            send("Well, I believe you awake...");
            isAwake = true;
        } else {
            send("Wrong key");
        }

    }

    private String generateKey() {
        Random rand = new Random();
        String alphabet = "abcdefghijklmnopqrstuwvxyz_";
        char[] key = new char[KEY_SIZE];
        for(int i = 0; i < key.length; i++) {
            key[i] = alphabet.charAt(rand.nextInt(alphabet.length()));
        }
        return new String(key);
    }
}
