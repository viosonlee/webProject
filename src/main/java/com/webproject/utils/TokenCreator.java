package com.webproject.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.webproject.models.User;

public class TokenCreator {
    public static String getToken(User user) {
        return JWT.create()
                .withAudience(user.getAccount())
                .sign(Algorithm.HMAC256(user.getPassword()));
    }
}
