package org.example.Curreny;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

public class CurrencyService {
    public Double getCurrencyPrice(String code){
        Double price = null;
        try {
            URL url = new URL("https://nbu.uz/uz/exchange-rates/json");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();

            List<ExchangeRate> exchangeRates = objectMapper.readValue(inputStream,
                    new TypeReference<List<ExchangeRate>>() {
            });
            for (ExchangeRate e:exchangeRates) {
                if(e.getCode().equals(code)){
                    price=e.getCbPrice();
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return price;
    }
    public boolean search(String code){
        boolean result=false;
        try {
            URL url = new URL("https://nbu.uz/uz/exchange-rates/json");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();

            List<ExchangeRate> exchangeRates = objectMapper.readValue(inputStream,
                    new TypeReference<List<ExchangeRate>>() {
                    });
            for (ExchangeRate e:exchangeRates) {
                if(e.getCode().equals(code)){
                    result=true;
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


}
