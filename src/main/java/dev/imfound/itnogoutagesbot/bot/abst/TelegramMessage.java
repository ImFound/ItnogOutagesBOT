package dev.imfound.itnogoutagesbot.bot.abst;

import dev.imfound.itnogoutagesbot.bot.BotTelegram;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class TelegramMessage {

    public abstract void message(Message message, BotTelegram botTelegram) throws TelegramApiException;

}
