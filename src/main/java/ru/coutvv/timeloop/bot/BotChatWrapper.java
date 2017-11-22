package ru.coutvv.timeloop.bot;

import org.apache.log4j.Logger;
import ru.coutvv.timeloop.io.ChatObserver;
import ru.coutvv.timeloop.io.ObservableChat;

import java.time.LocalTime;
import static ru.coutvv.timeloop.bot.BotCommandConsts.*;

/**
 * class for managing contexts and react on system command from user
 *
 * @author coutvv    21.11.2017
 */
public class BotChatWrapper extends ObservableChat implements ChatObserver{

    private static final Logger logger = Logger.getLogger(BotChatWrapper.class);

    private final ObservableChat chat;
    private Context context;
    private final SystemSettings settings = new SystemSettings();

    public BotChatWrapper(ObservableChat chat) {
       this.chat = chat;
       chat.addObserver(this);
    }

    @Override
    public void handleEvent(String message) {
        if(message == null) {

        } else if(message.equals(START)) {
            logger.warn("new user");
            send("Hello! I'm glad to see you in my alarm clock chat!");
            send("to start using alarm clock send '/set', default time 5:50");
            send("to stop alarm clock send '/stop'");
            send("to set time send '/set hh:mm' \n for example: \n /set 5:15 \n set alarm clock to 5:15");
        } else if(message.equals(STOP)) {
            context.kill();
            context = null;
        } else if(message.equals(HELP)) {
            send("to start using alarm clock send '/set', default time 5:50");
            send("to stop alarm clock send '/stop'");
            send("to set time send '/set hh:mm' \n for example: \n /set 5:15 \n set alarm clock to 5:15");
        } else if(message.startsWith(SET)) {
            String ss[] = message.split(" ");
            if(ss.length == 2) {
                String hourMin[] = ss[1].split(":");
                if(hourMin.length == 2 && isNumeric(hourMin[0]) && isNumeric(hourMin[1])) {
                    int hh = Integer.parseInt(hourMin[0]);
                    int mm = Integer.parseInt(hourMin[1]);
                    if(hh >= 0 && hh < 24 && mm >= 0 && mm < 60) {
                        settings.alarmTime = LocalTime.of(hh, mm);
                        if(context != null)
                            context.kill();
                        context = new Context(this, settings);
                        context.run();
                    } else
                        send("not correct time");
                } else {
                    send("not correct time");
                }
            } else { //if user send just '/set'
                if(context == null) {
                    context = new Context(this, settings);
                    context.run();
                } else {
                    send("can't start again – last session is run");
                }
            }

        } else {
            notifyObservers(message);
        }
    }

    @Override
    public void send(String message) {
        chat.send(message);
    }

    @Override
    public void closeChat() {
        chat.closeChat();
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
