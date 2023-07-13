package dev.imfound.itnogoutagesbot.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class MessageBuilder {


    private final List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
    private int messageId;
    private Long chatId;
    private String text;
    private List<InlineKeyboardButton> row = null;

    private MessageBuilder() {
    }

    public static MessageBuilder create(Long chatId) {
        MessageBuilder builder = new MessageBuilder();
        builder.setChatId(chatId);
        return builder;
    }

    public MessageBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public MessageBuilder setMessageId(int id) {
        this.messageId = id;
        return this;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public MessageBuilder row() {
        this.row = new ArrayList<>();
        return this;
    }

    public MessageBuilder button(String text, String callbackData, String url) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        if (url != null) {
            button.setUrl(url);
        }
        row.add(button);
        return this;
    }

    public MessageBuilder endRow() {
        this.keyboard.add(this.row);
        this.row = null;
        return this;
    }


    public SendMessage build() {
        SendMessage message = new SendMessage();

        message.setChatId(String.valueOf(chatId));
        message.enableHtml(true);
        message.setText(text);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

    public EditMessageText buildEditMessage() {
        EditMessageText message = new EditMessageText();

        message.setMessageId(messageId);
        message.setChatId(String.valueOf(chatId));
        message.enableHtml(true);
        message.setText(text);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

}
