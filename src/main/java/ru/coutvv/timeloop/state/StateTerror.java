package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.ioservice.ObservableChat;
import ru.coutvv.timeloop.util.WaitUtil;

import java.util.Random;
import java.util.function.BooleanSupplier;

/**
 * @author coutvv    20.11.2017
 */
public class StateTerror extends State {

    private boolean isAwake = false;
    private BooleanSupplier waitCondition = () -> isAwake && !skip;

    private final int KEY_SIZE = 20;
    private final int RESET_KEY_TIMEOUT = 60_000;
    private String key;

    public StateTerror(Context context) {
        super(context);
    }

    @Override
    public void operation() {
        while(!isAwake) {
            key = generateKey();
            send("Wake up!");
            send("Key is:\n" + key);
            WaitUtil.lagUntil(() -> isAwake, RESET_KEY_TIMEOUT);
        }
        State next = new StateStretching(context);
        context.setState(next);
    }

    @Override
    public void handleMsg(String message) {
        if(message.equals(key)){
            isAwake = true;
            send("key confirmed");
        } else {
            send("wrong key");
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
