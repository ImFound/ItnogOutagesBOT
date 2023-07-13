package dev.imfound.itnogoutagesbot.bot.callbacks;

import dev.imfound.itnogoutagesbot.bot.BotTelegram;
import dev.imfound.itnogoutagesbot.bot.abst.TelegramCallback;
import dev.imfound.itnogoutagesbot.enums.ReportOutagePhase;
import dev.imfound.itnogoutagesbot.main.ITNOGOutagesBOT;
import dev.imfound.itnogoutagesbot.messages.Messages;
import dev.imfound.itnogoutagesbot.obj.ReportOutage;
import dev.imfound.itnogoutagesbot.utils.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ReportOutageCallback extends TelegramCallback {
    @Override
    public void callback(CallbackQuery query, BotTelegram botTelegram) throws TelegramApiException {
        Messages messages = new Messages();

        EditMessageText text = MessageBuilder.create(query.getMessage().getChatId())
                .setText(messages.service)
                .setMessageId(query.getMessage().getMessageId())
                .row()
                .button("❌ | Annulla", "cancel", "")
                .endRow()
                .buildEditMessage();

        botTelegram.executeAsync(text);


        ReportOutage outage = new ReportOutage("", "", "", ReportOutagePhase.SELECT_SERVICE);
        ITNOGOutagesBOT.getInstance().getOutageReportsPhase().put(query.getFrom().getId(), outage);
    }
}
