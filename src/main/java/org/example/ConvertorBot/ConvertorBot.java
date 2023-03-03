package org.example.ConvertorBot;

import org.example.Curreny.CurrencyService;
import org.example.User.UserState;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class ConvertorBot extends TelegramLongPollingBot implements BotUtils{
    static Map<Long,String> code=new HashMap<>();
    static Map<Long,Double> end=new HashMap<>();
    static Map<Long,String> toOrFrom=new HashMap<>();
    static ConvertorBotService convertorBotService=new ConvertorBotService();
    static CurrencyService currencyService=new CurrencyService();
    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        UserState userState=null;
        Double x=null;
        if(update.hasMessage()){
            Message message = update.getMessage();
            Long chatId = update.getMessage().getChatId();
            String text=message.getText();
            if(text.equals("/start")){
                userState=UserState.START;
            }
            else if(message.getText().equals("To sum")||message.getText().equals("From sum")){
                toOrFrom.put(chatId,message.getText());
                    userState=UserState.CHOOSE_CURRENY;
            }
            else if (currencyService.search(message.getText())){
                code.put(chatId,message.getText());
                userState=UserState.ENTER_AMOUNT;
            }
            else if (message.getText().equals("◀️back")){
                userState=UserState.START;
            }
            else if (Double.parseDouble(message.getText())>0) {
                Double price=currencyService.getCurrencyPrice(code.get(chatId));
                if(toOrFrom.get(chatId).equals("To sum")) {
                    x = Double.parseDouble(message.getText()) * price;
                    end.put(chatId, x);
                    userState=UserState.END_FROM;
                }
                else if (toOrFrom.get(chatId).equals("From sum")) {
                    x = Integer.parseInt(message.getText()) / price;
                    end.put(chatId, x);
                    userState=UserState.END_FROM;
                }

            }

            switch (userState){
                case START -> {
                    try {
                        execute(convertorBotService.menu(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case CHOOSE_CURRENY -> {
                    try {
                        execute(convertorBotService.fromSom(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case ENTER_AMOUNT -> {
                    try {
                        execute(convertorBotService.enterAmountFromSom(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case END_FROM -> {
                    try {
                        execute(convertorBotService.result(chatId,end.get(chatId)));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
