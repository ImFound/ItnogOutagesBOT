package dev.imfound.itnogoutagesbot.bot.receivers;

import dev.imfound.itnogoutagesbot.bot.BotTelegram;
import dev.imfound.itnogoutagesbot.bot.abst.TelegramMessage;
import dev.imfound.itnogoutagesbot.bot.messages.SelectDescriptionMessage;
import dev.imfound.itnogoutagesbot.bot.messages.SelectServiceMessage;
import dev.imfound.itnogoutagesbot.bot.messages.SelectTimeMessage;
import dev.imfound.itnogoutagesbot.bot.messages.StartMessage;
import dev.imfound.itnogoutagesbot.main.ITNOGOutagesBOT;
import dev.imfound.itnogoutagesbot.obj.ReportOutage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramMessageReceiver extends TelegramMessage {
    @Override
    public void message(Message message, BotTelegram botTelegram) throws TelegramApiException {

        if (message.getText().equalsIgnoreCase("/start")) {
            if (message.isGroupMessage() || message.isSuperGroupMessage()) return;
            new StartMessage().message(message, botTelegram);
        } else {
            if (ITNOGOutagesBOT.getInstance().getOutageReportsPhase().containsKey(message.getChatId())) {
                ReportOutage reportOutage = ITNOGOutagesBOT.getInstance().getOutageReportsPhase().get(message.getChatId());

                switch (reportOutage.getReportOutagePhase()) {
                    case SELECT_SERVICE: {
                        new SelectServiceMessage().message(message, botTelegram);
                        break;
                    }
                    case SELECT_TIME: {
                        new SelectTimeMessage().message(message, botTelegram);
                        break;
                    }
                    case SELECT_DESCRIPTION: {
                        new SelectDescriptionMessage().message(message, botTelegram);
                        break;
                    }
                }
            }
        }

    }
}
