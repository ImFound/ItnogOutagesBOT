package dev.imfound.itnogoutagesbot.bot.receivers;

import dev.imfound.itnogoutagesbot.bot.BotTelegram;
import dev.imfound.itnogoutagesbot.bot.abst.TelegramCallback;
import dev.imfound.itnogoutagesbot.bot.callbacks.ConfirmCallback;
import dev.imfound.itnogoutagesbot.bot.callbacks.ReportOutageCallback;
import dev.imfound.itnogoutagesbot.main.ITNOGOutagesBOT;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramCallbackReceiver extends TelegramCallback {
    @Override
    public void callback(CallbackQuery query, BotTelegram botTelegram) throws TelegramApiException {
        switch (query.getData()) {
            case "reportOutage": {
                new ReportOutageCallback().callback(query, botTelegram);
                break;
            }
            case "cancel": {
                ITNOGOutagesBOT.getInstance().getOutageReportsPhase().remove(query.getFrom().getId());
                break;
            }
            case "confirm": {
                new ConfirmCallback().callback(query, botTelegram);
                break;
            }
        }
    }
}
