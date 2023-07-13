package dev.imfound.itnogoutagesbot.main;

import dev.imfound.itnogoutagesbot.bot.BotTelegram;
import dev.imfound.itnogoutagesbot.mysql.DataSource;
import dev.imfound.itnogoutagesbot.obj.settings.OutagesSettings;
import dev.imfound.itnogoutagesbot.obj.ReportOutage;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;

public class ITNOGOutagesBOT {

    @Getter private static ITNOGOutagesBOT instance;
    @Getter private Logger logger;
    @Getter private DataSource dataSource;
    @Getter private TelegramLongPollingBot longPollingBot;
    @Getter private OutagesSettings settings;
    @Getter private Map<Long, ReportOutage> outageReportsPhase;

    public ITNOGOutagesBOT(OutagesSettings outagesSettings) throws TelegramApiException {
        instance = this;

        settings = outagesSettings;
        outageReportsPhase = new HashMap<>();

        logger = LoggerFactory.getLogger("ITNOGOutagesBOT");
        logger.info("Starting ITNOGOutagesBOT");
       /* logger.info("Loading MySQL...");
        dataSource = new DataSource(outagesSettings.getMySQLSettings());
        logger.info("MySQL Loaded!");*/
        logger.info("Starting Telegram BOT...");
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        longPollingBot = new BotTelegram(outagesSettings.getBotSettings());
        botsApi.registerBot(longPollingBot);
        logger.info("Telegram BOT started!");

        logger.info("ITNOGOutagesBOT started!");
    }

}
