package dev.imfound.itnogoutagesbot.bot.abst;

import dev.imfound.itnogoutagesbot.bot.BotTelegram;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class TelegramCallback {

    public abstract void callback(CallbackQuery query, BotTelegram botTelegram) throws TelegramApiException;

}
