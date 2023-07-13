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

public class SelectDescriptionMessage extends TelegramMessage {
    @Override
    public void message(Message message, BotTelegram botTelegram) throws TelegramApiException {
        Messages messages = new Messages();
        ReportOutage reportOutage = ITNOGOutagesBOT.getInstance().getOutageReportsPhase().get(message.getChatId());

        reportOutage.setDescription(message.getText());
        reportOutage.setReportOutagePhase(ReportOutagePhase.END);

        ITNOGOutagesBOT.getInstance().getOutageReportsPhase().remove(message.getChatId(), reportOutage);
        ITNOGOutagesBOT.getInstance().getOutageReportsPhase().put(message.getChatId(), reportOutage);

        SendMessage sendMessage = MessageBuilder.create(message.getChatId())
                .setText(String.format("<b>ITNOG</b> - Segnalazione disservizi\n" +
                        "\n" +
                        "» Ecco un riepilogo di quello che hai segnalato:\n" +
                        "<b>Servizio</b> %s\n" +
                        "<b>Da quanto tempo?</b> %s\n" +
                        "<b>Descrizione</b> %s", reportOutage.getService(), reportOutage.getTime(), reportOutage.getDescription()))
                .row()
                .button("✅ | Conferma", "confirm", "")
                .button("❌ | Annulla", "cancel", "")
                .endRow()
                .build();

        botTelegram.executeAsync(sendMessage);
    }
}
