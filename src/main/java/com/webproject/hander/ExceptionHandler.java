package com.webproject.hander;


import com.webproject.exceptions.TokenException;
import com.webproject.result.Result;
import com.webproject.result.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(TokenException.class)
    @ResponseBody
    public Result<Object> handleTokenException(HttpServletRequest request, TokenException e) {
        if (e != null) {
            return new Result<>(e.getResultType());
        } else {
            return new Result<>(ResultType.unkown_error);
        }
    }
}
