package com.example.main;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
//import org.json.JSONObject;

import com.example.main.utils.JWTTokenGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/*
 * 나의 전체 보유 자산 조회
 */
@Slf4j
public class getAccounts {
    private static final String ACCESS_KEY = "qwibojLwX90CBNpka3HY2aGxwPLg25Hr0CpbGwPw";
    private static final String SECRET_KEY = "1BlG9qjJxS7xO5GD1Pt2xySAQr51SiAYnj95EuGN";

    public static void main(String[] args) throws ClientProtocolException, IOException {

        String token = JWTTokenGenerator.generateToken(ACCESS_KEY, SECRET_KEY);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://api.upbit.com/v1/accounts");
        request.addHeader("Authorization", token);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // HttpEntity httpEntity = response.getEntity();
            // String responseBody = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode result = objectMapper.readTree(EntityUtils.toString(response.getEntity()));
            // System.out.println("result: " + result);
            log.info(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
    }

}