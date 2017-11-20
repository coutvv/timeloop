package ru.coutvv.timeloop.ioservice;

/**
 * @author coutvv    20.11.2017
 */
public abstract class ObservableChat {
    private ChatObserver chatObserver;
    public void setObserver(ChatObserver observer) {
        chatObserver = observer;
    }
    public void notifyObserver(String message) {
        chatObserver.handleEvent(message);
    }
    public abstract void send(String message);

    public abstract void closeChat();
}
