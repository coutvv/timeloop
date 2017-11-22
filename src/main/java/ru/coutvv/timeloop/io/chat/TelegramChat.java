package ru.coutvv.timeloop.io.chat;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import ru.coutvv.timeloop.io.ObservableChat;

import java.util.function.Consumer;

/**
 * @author coutvv    22.11.2017
 */
public class TelegramChat extends ObservableChat {

    private static Logger logger = Logger.getLogger(TelegramChat.class);
    private final TelegramBotChannel bot;


    private final Long chatId;

    public TelegramChat(TelegramBotChannel bot, Long chatId) {
        Consumer<String> sendToBot = (msg) -> {
            notifyObservers(msg);
        };
        this.bot = bot;
        this.chatId = chatId;
        bot.registerChat(chatId, sendToBot);


    }

    @Override
    public void send(String message) {
        bot.sendToChat(message, chatId);
    }

    @Override
    public void closeChat() {

    }
}
