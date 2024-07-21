package com.example.main;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * 현재가격 조회
 */
@Slf4j
public class currentPrice {
    public static void main(String[] args) throws IOException {

        String market = "KRW-STX";
        String trade_price;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/ticker")
                .get()
                .addHeader("accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        log.info(response.toString());
    }
}
