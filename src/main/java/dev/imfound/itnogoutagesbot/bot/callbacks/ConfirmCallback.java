package dev.imfound.itnogoutagesbot.bot.callbacks;

import dev.imfound.itnogoutagesbot.bot.BotTelegram;
import dev.imfound.itnogoutagesbot.bot.abst.TelegramCallback;
import dev.imfound.itnogoutagesbot.main.ITNOGOutagesBOT;
import dev.imfound.itnogoutagesbot.messages.Messages;
import dev.imfound.itnogoutagesbot.obj.ReportOutage;
import dev.imfound.itnogoutagesbot.utils.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ConfirmCallback extends TelegramCallback {
    @Override
    public void callback(CallbackQuery query, BotTelegram botTelegram) throws TelegramApiException {
        ReportOutage outage = ITNOGOutagesBOT.getInstance().getOutageReportsPhase().get(query.getMessage().getChatId());

        Messages messages = new Messages();
        EditMessageText message = MessageBuilder.create(query.getMessage().getChatId())
                .setText(messages.confirm)
                .setMessageId(query.getMessage().getMessageId())
                .buildEditMessage();

        botTelegram.executeAsync(message);

        int id = 0;

        SendMessage outageMessage = MessageBuilder.create(ITNOGOutagesBOT.getInstance().getSettings().getBotSettings().getChatId())
                .setText(String.format("<b>ITNOG</b> - Nuovo disservizio\n" +
                        "\n" +
                        "» E' stato segnalato un nuovo disservizio da %s\n" +
                        "<b>Servizio</b> %s\n" +
                        "<b>Da quanto tempo?</b> %s\n" +
                        "<b>Descrizione</b> %s\n" +
                        "\n" +
                        "<i>Utilizza i bottoni qua sotto per interagire</i>",
                        "<a href=\"tg://user?id="+query.getFrom().getId()+"\">"+query.getFrom().getId()+"</a>",
                        outage.getService(),
                        outage.getTime(),
                        outage.getDescription()))
                .row()
                .button("\uD83D\uDD34 | Si verifica ancora » 0", "notResolved"+id, "")
                .button("\uD83D\uDFE2 | Sembra risolto » 0", "resolved"+id, "")
                .endRow()
                .build();

        outageMessage.setMessageThreadId(ITNOGOutagesBOT.getInstance().getSettings().getBotSettings().getThreadId());
        botTelegram.execute(outageMessage);
        ITNOGOutagesBOT.getInstance().getOutageReportsPhase().remove(query.getMessage().getChatId());
    }
}
