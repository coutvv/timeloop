package ru.coutvv.timeloop.ioservice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coutvv    20.11.2017
 */
public abstract class ObservableChat {
    private List<ChatObserver> observers = new ArrayList<>();

    public void addObserver(ChatObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ChatObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for(ChatObserver obs : observers) {
            obs.handleEvent(message);
        }
    }

    public abstract void send(String message);

    public abstract void closeChat();
}
