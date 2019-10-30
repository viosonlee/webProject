package com.webproject.exceptions;

import com.webproject.result.ResultType;

public class TokenException extends RuntimeException {
    private ResultType resultType = ResultType.invaid_token;

    public TokenException(ResultType resultType) {
        this.resultType = resultType;
    }

    public TokenException() {
    }

    public ResultType getResultType() {
        return resultType;
    }
}
