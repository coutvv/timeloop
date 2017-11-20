package ru.coutvv.timeloop.state;

import ru.coutvv.timeloop.Context;
import ru.coutvv.timeloop.ioservice.ObservableChat;
import ru.coutvv.timeloop.util.WaitUtil;

import java.util.Random;

/**
 * @author coutvv    20.11.2017
 */
public class StateTerror extends State {

    private boolean isAwake = false;

    private final int KEY_SIZE = 20;
    private final int RESET_KEY_TIMEOUT = 60000;
    private String key;

    public StateTerror(Context context, ObservableChat chat) {
        super(context, chat);
    }

    @Override
    public void operation() {
        while(!isAwake) {
            key = generateKey();
            chat.send("Wake up!");
            chat.send("Key is:\n" + key);
            WaitUtil.lag(RESET_KEY_TIMEOUT);
        }
        State next = new StateStretching(context, chat);
        chat.setObserver(next);
        context.setState(next);
    }

    @Override
    public void handleEvent(String message) {
        if(message.equals(key)){
            isAwake = true;
            chat.send("key confirmed");
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
