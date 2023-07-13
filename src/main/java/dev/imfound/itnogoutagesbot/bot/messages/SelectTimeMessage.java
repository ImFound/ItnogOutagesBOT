package dev.imfound.itnogoutagesbot.bot.messages;

import dev.imfound.itnogoutagesbot.bot.BotTelegram;
import dev.imfound.itnogoutagesbot.bot.abst.TelegramMessage;
import dev.imfound.itnogoutagesbot.enums.ReportOutagePhase;
import dev.imfound.itnogoutagesbot.main.ITNOGOutagesBOT;
import dev.imfound.itnogoutagesbot.messages.Messages;
import dev.imfound.itnogoutagesbot.obj.ReportOutage;
import dev.imfound.itnogoutagesbot.utils.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SelectTimeMessage extends TelegramMessage {
    @Override
    public void message(Message message, BotTelegram botTelegram) throws TelegramApiException {
        Messages messages = new Messages();
        ReportOutage reportOutage = ITNOGOutagesBOT.getInstance().getOutageReportsPhase().get(message.getChatId());

        reportOutage.setTime(message.getText());
        reportOutage.setReportOutagePhase(ReportOutagePhase.SELECT_DESCRIPTION);

        ITNOGOutagesBOT.getInstance().getOutageReportsPhase().remove(message.getChatId(), reportOutage);
        ITNOGOutagesBOT.getInstance().getOutageReportsPhase().put(message.getChatId(), reportOutage);

        SendMessage sendMessage = MessageBuilder.create(message.getChatId())
                .setText(messages.description)
                .row()
                .button("❌ | Annulla", "cancel", "")
                .endRow()
                .build();

        botTelegram.executeAsync(sendMessage);
    }
}