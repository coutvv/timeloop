package ru.coutvv.timeloop.io.chat;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.coutvv.timeloop.bot.BotChatWrapper;
import ru.coutvv.timeloop.io.ObservableChat;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author coutvv    22.11.2017
 */
public class TelegramBotChannel extends TelegramLongPollingBot {
    private static final Logger logger = Logger.getLogger(TelegramBotChannel.class);

    private final String botName;
    private final String botToken;

    private final Map<Long, Consumer<String>> chats = new HashMap<>();


    public TelegramBotChannel(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;

    }

    @Override
    public void onUpdateReceived(Update update) {
        String msg = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        Consumer<String> sender = chats.get(chatId);
        if(sender != null) {
            sender.accept(msg);
        } else {
            ObservableChat chat = new TelegramChat(this, chatId);
            new BotChatWrapper(chat);
            chat.notifyObservers(msg);
            logger.info("user has registered: " + update.getMessage().getFrom().toString());
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public void sendToChat(String message, Long chatId) {
        SendMessage msg = new SendMessage().setChatId(chatId).setText(message);
        try {
            msg.setParseMode("html");
            execute(msg);
        } catch (TelegramApiException e) {
            logger.error("can't send message: " + message + "\n to: " + chatId + "\n" + e.getLocalizedMessage());
        }
    }

    public void registerChat(Long chatId, Consumer<String> receive) {
        chats.put(chatId, receive);
    }
}
