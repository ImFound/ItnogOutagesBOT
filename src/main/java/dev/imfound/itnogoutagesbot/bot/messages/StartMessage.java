package dev.imfound.itnogoutagesbot.bot.messages;

import dev.imfound.itnogoutagesbot.bot.BotTelegram;
import dev.imfound.itnogoutagesbot.bot.abst.TelegramMessage;
import dev.imfound.itnogoutagesbot.messages.Messages;
import dev.imfound.itnogoutagesbot.utils.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartMessage extends TelegramMessage {
    @Override
    public void message(Message message, BotTelegram botTelegram) throws TelegramApiException {
        Messages messages = new Messages();
        SendMessage startMessage = MessageBuilder.create(message.getChatId())
                .setText(messages.start)
                .row()
                .button("\uD83D\uDD34 | Segnala un disservizio", "reportOutage", "")
                .endRow()
                .build();

        botTelegram.execute(startMessage);
    }
}
