package com.quickly.xqw.model;

import com.google.gson.Gson;

public class ErrorMessage {


    /**
     * code : 403
     * msg : 请登录小程序！
     * timestamp : 1538983430
     * nonceStr : dc3aRbwG
     * data : null
     */

    private int code;
    private String msg;
    private int timestamp;
    private String nonceStr;
    private Object data;

    public static ErrorMessage objectFromData(String str) {

        return new Gson().fromJson(str, ErrorMessage.class);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
