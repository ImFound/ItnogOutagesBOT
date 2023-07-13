package dev.imfound.itnogoutagesbot;

import dev.imfound.itnogoutagesbot.main.ITNOGOutagesBOT;
import dev.imfound.itnogoutagesbot.obj.settings.OutagesSettings;
import dev.imfound.itnogoutagesbot.obj.settings.BOTSettings;
import dev.imfound.itnogoutagesbot.obj.settings.MySQLSettings;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) throws TelegramApiException {
        OutagesSettings settings = new OutagesSettings(
                new MySQLSettings(
                        System.getenv("MYSQL_HOST"),
                        Integer.parseInt(System.getenv("MYSQL_PORT")),
                        System.getenv("MYSQL_DATABASE"),
                        System.getenv("MYSQL_USERNAME"),
                        System.getenv("MYSQL_PASSWORD")
                ),
                new BOTSettings(
                        System.getenv("TELEGRAM_USERNAME"),
                        System.getenv("TELEGRAM_TOKEN"),
                        Long.parseLong(System.getenv("TELEGRAM_CHAT_ID")),
                        Integer.parseInt(System.getenv("TELEGRAM_THREAD_ID"))
                )
        );


        new ITNOGOutagesBOT(settings);
    }

}
