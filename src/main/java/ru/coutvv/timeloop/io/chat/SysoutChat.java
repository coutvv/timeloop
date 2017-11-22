package ru.coutvv.timeloop.io.chat;

import org.apache.log4j.Logger;
import ru.coutvv.timeloop.io.ObservableChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author coutvv    20.11.2017
 */
public class SysoutChat extends ObservableChat {
    private final static Logger logger = Logger.getLogger(SysoutChat.class);
    
    private boolean isRead = true;
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public SysoutChat() {
        Runnable read = () -> {
            while(isRead) {

                String message = null;
                try {
                    message = br.readLine();
                } catch (IOException e) {
                    logger.error("problem with reading in console:\n" + e.getLocalizedMessage());
                }
                this.notifyObservers(message);
                Thread.yield();
            }
        };
        Thread th = new Thread(read);
        th.start();
    }

    @Override
    public void send(String message) {
        System.out.println(message);
    }

    @Override
    public void closeChat() {
        isRead = false;
        try {
            System.in.close();
        } catch (IOException e) {
            logger.error("problem with closing system.in \n"+ e.getLocalizedMessage());
        }
    }


}
