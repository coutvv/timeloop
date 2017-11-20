package ru.coutvv.timeloop.ioservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author coutvv    20.11.2017
 */
public class SysoutChat extends ObservableChat {
    private boolean isRead = true;
    public SysoutChat() {
        Runnable read = () -> {
            while(isRead) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                String message = null;
                try {
                    message = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.notifyObserver(message);
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
    }


}
