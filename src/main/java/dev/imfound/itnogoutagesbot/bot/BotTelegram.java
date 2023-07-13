package dev.imfound.itnogoutagesbot.bot;

import dev.imfound.itnogoutagesbot.bot.receivers.TelegramCallbackReceiver;
import dev.imfound.itnogoutagesbot.bot.receivers.TelegramMessageReceiver;
import dev.imfound.itnogoutagesbot.main.ITNOGOutagesBOT;
import dev.imfound.itnogoutagesbot.obj.settings.BOTSettings;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotTelegram extends TelegramLongPollingBot {

    private BOTSettings botSettings;

    public BotTelegram(BOTSettings botSettings) {
        this.botSettings = botSettings;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasCallbackQuery()) {
            try {
                new TelegramCallbackReceiver().callback(update.getCallbackQuery(), this);
            } catch (TelegramApiException e) {
                ITNOGOutagesBOT.getInstance().getLogger().error("Error when processing a callback: "+e.getMessage());
            }
        }else if(update.hasMessage()) {
            try {
                new TelegramMessageReceiver().message(update.getMessage(), this);
            } catch (TelegramApiException e) {
                ITNOGOutagesBOT.getInstance().getLogger().error("Error when processing a message: "+e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botSettings.getUsername();
    }

    @Override
    public String getBotToken() {
        return botSettings.getToken();
    }

}
