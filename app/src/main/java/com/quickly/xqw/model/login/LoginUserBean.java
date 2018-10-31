package com.quickly.xqw.model.login;

import com.google.gson.Gson;

public class LoginUserBean {


    /**
     * code : 0
     * msg : 请求成功！
     * timestamp : 1539161139
     * nonceStr : OrGKeX7r
     * data : {"gender":"女","avatar":"http://image.luosen365.com/head_8.png","user_session_":"ab35c5ab85e744e686615b1c0167d815","username":"foxman"}
     */

    private int code;
    private String msg;
    private int timestamp;
    private String nonceStr;
    private DataBean data;

    public static LoginUserBean objectFromData(String str) {

        return new Gson().fromJson(str, LoginUserBean.class);
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
         * gender : 女
         * avatar : http://image.luosen365.com/head_8.png
         * user_session_ : ab35c5ab85e744e686615b1c0167d815
         * username : foxman
         */

        private String gender;
        private String avatar;
        private String user_session_;
        private String username;
        private String nickname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUser_session_() {
            return user_session_;
        }

        public void setUser_session_(String user_session_) {
            this.user_session_ = user_session_;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
