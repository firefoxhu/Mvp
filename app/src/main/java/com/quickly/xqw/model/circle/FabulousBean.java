package com.quickly.xqw.model.circle;

import com.google.gson.Gson;

public class FabulousBean {


    /**
     * code : 0
     * msg : 请求成功！
     * timestamp : 1539269783
     * nonceStr : ktazap9a
     * data : {"id":462,"articleId":443,"username":""}
     */

    private int code;
    private String msg;
    private int timestamp;
    private String nonceStr;
    private DataBean data;

    public static FabulousBean objectFromData(String str) {

        return new Gson().fromJson(str, FabulousBean.class);
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 462
         * articleId : 443
         * username :
         */

        private int id;
        private int articleId;
        private String username;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
