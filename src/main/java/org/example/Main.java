package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ConvertorBot.ConvertorBot;
import org.example.Curreny.CurrencyService;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi= null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new ConvertorBot());
            System.out.println("Bot is running");
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(()->{
            System.out.println("adsdsaadsasd");
        });
    }

}