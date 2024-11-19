package com.webproject.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.webproject.annotations.PassToken;
import com.webproject.annotations.UserLoginToken;
import com.webproject.exceptions.TokenException;
import com.webproject.models.User;
import com.webproject.result.ResultType;
import com.webproject.services.UserService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {   // 如果不是映射到方法直接通过
            return true;
        }
        String token = request.getHeader("token");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if (token == null) {
                    throw new TokenException();
//                    throw new RuntimeException("用户token为空");
                }
                String userAccount;
                try {
                    userAccount = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException e) {
                    throw new RuntimeException("401");
                }
                User user = userService.findUserByAccount(userAccount);
                if (user == null) {
                    throw new TokenException(ResultType.not_find_account);
                }
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }

        return false;
    }
}
