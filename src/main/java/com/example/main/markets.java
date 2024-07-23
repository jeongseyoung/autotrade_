package com.example.main;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class Markets {
    /*
     * 마켓에 있는 모든 코인 가져오기
     */
    public void marketList() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/market/all?isDetails=false")
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        // System.out.println("response: " + response.body().string());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode result = objectMapper.readTree(response.body().string());
        log.info(result.toString());
    }
}
