package org.example.ConvertorBot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class ConvertorBotService {
    public SendMessage menu (Long chatId){
        SendMessage sendMessage=new SendMessage(chatId.toString(),"Choose");
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows=new ArrayList<>();
        KeyboardRow row=new KeyboardRow();

        row.add("From sum");
        rows.add(row);
        row=new KeyboardRow();

        row.add("To sum");
        rows.add(row);
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
    public SendMessage fromSom(Long chatId){
        SendMessage sendMessage=new SendMessage(chatId.toString(),"Choose");
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows=new ArrayList<>();
        KeyboardRow row=new KeyboardRow();

        row.add("EUR");
        row.add("TRY");
        row.add("KZT");
        rows.add(row);
        row=new KeyboardRow();

        row.add("RUB");
        row.add("USD");
        row.add("UAH");
        rows.add(row);
        row=new KeyboardRow();

        row.add("◀️back");
        rows.add(row);
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
    public SendMessage enterAmountFromSom(Long chatId){
        return new SendMessage(chatId.toString(),"Enter amount");
    }
    public SendMessage result(Long chatId,Double x){
        String s=x+"";
        return  new SendMessage(chatId.toString(), s);
    }
}
