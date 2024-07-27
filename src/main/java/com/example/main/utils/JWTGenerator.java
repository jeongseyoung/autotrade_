package com.example.main.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTGenerator {
    public static String generateToken(String accessKey, String secretKey) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        // Header
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("typ", "JWT");
        headerClaims.put("alg", "HS256");
        // Payload
        String jwtToken = JWT.create()
                .withHeader(headerClaims)
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);
        // String authenticationToken = "Bearer " + jwtToken;
        return jwtToken;
    }
}