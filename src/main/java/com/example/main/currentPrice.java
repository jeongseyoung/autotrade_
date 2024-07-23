package com.example.main;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.main.vo.currentPriceVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * 현재가격 조회
 * response.body().string() -> JSONArray 형태로 날라옴. -> 생성자로 변환하려면 생성자를 배열형태로 만들고 변환해야함.
 */
@Slf4j
public class currentPrice {
    public static void main(String[] args) throws IOException {

        String market = "KRW-STX";
        // String trade_price;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/ticker?markets=" + market)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String r = response.body().string();

        ObjectMapper objectMapper = new ObjectMapper();
        currentPriceVO[] cpVO = objectMapper.readValue(r, currentPriceVO[].class); // <<<
        log.info(cpVO[0].getMarket());
    }
}
