package com.example.main.order;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.main.CurrentPrice;
import com.example.main.GetAccounts;
import com.example.main.vo.CurrentPriceVO;
import com.example.main.vo.PostOrdersVO;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class PostOrders {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String accessKey = System.getenv("");
        String secretKey = System.getenv("");
        String serverUrl = System.getenv("https://api.upbit.com/v1/orders");
        CurrentPrice cp = new CurrentPrice();
        CurrentPriceVO[] currentPriceurrentPriceVO = cp.getCurrentPrice("KRW-BTC");

        PostOrdersVO pVO = new PostOrdersVO("KRW-BTC", "bid", "0.000001",
                String.valueOf(currentPriceurrentPriceVO[0].getTrade_price()), "limit");

        // ㄱㄱㄱㄱㄱ
        HashMap<String, String> params = new HashMap<>();
        params.put("market", pVO.getMarket());
        params.put("side", pVO.getSide());
        params.put("volume", pVO.getVolume());
        params.put("price", pVO.getPrice());
        params.put("ord_type", pVO.getOrd_type());

        ArrayList<String> queryElements = new ArrayList<>();
        for (Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
            log.info(queryElements.toString());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(serverUrl);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);
            request.setEntity(new StringEntity(new Gson().toJson(params)));

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println(EntityUtils.toString(entity, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
