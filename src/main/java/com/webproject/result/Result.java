package com.webproject.result;

public class Result<T> {
    private String meesage;
    private int code;
    private T data;

    public Result() {
        this.code = ResultType.success.getCode();
        this.meesage = ResultType.success.getMessage();
    }

    public Result(T data) {
        this();
        this.data = data;
    }

    public Result(ResultType type) {
        this.code = type.getCode();
        this.meesage = type.getMessage();
    }

    public Result(ResultType type, T data) {
        this(type);
        this.setData(data);
    }

    public String getMeesage() {
        return meesage;
    }

    public void setMeesage(String meesage) {
        this.meesage = meesage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
