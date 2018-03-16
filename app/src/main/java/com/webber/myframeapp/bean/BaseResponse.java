package com.webber.myframeapp.bean;

/**
 * Created by Webber on 2018/3/6.
 * Describe : 响应数据的基类
 */
public class BaseResponse<T> {

    private int code;
    private String message;
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
