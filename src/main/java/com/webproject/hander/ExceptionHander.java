package com.webproject.hander;


import com.webproject.exceptions.TokenException;
import com.webproject.result.Result;
import com.webproject.result.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHander {
    private Logger logger = LoggerFactory.getLogger(ExceptionHander.class);

    @ExceptionHandler(TokenException.class)
    @ResponseBody
    public Result handleTokenException(HttpServletRequest request, TokenException e) {
        if (e != null) {
            return new Result(((TokenException) e).getResultType());
        } else {
            return new Result(ResultType.unkown_error);
        }
    }
}
