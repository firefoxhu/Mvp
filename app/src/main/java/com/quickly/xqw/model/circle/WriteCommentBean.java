package com.quickly.xqw.model.circle;

import com.google.gson.Gson;

public class WriteCommentBean {


    /**
     * code : 0
     * msg : 请求成功！
     * timestamp : 1539241302
     * nonceStr : EiAuEBS9
     * data : {"data":{"commentId":453,"username":null,"avatar":"http://image.luosen365.com/head_1.png","nickname":"fire","content":"wobuzhidaofazhefjdsfsfsdjfsdalfj","fabulous":0,"location":"信阳市（默认）","createTime":"刚刚"}}
     */

    private int code;
    private String msg;
    private int timestamp;
    private String nonceStr;
    private DataBeanX data;

    public static WriteCommentBean objectFromData(String str) {

        return new Gson().fromJson(str, WriteCommentBean.class);
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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : {"commentId":453,"username":null,"avatar":"http://image.luosen365.com/head_1.png","nickname":"fire","content":"wobuzhidaofazhefjdsfsfsdjfsdalfj","fabulous":0,"location":"信阳市（默认）","createTime":"刚刚"}
         */

        private DataBean data;

        public static DataBeanX objectFromData(String str) {

            return new Gson().fromJson(str, DataBeanX.class);
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * commentId : 453
             * username : null
             * avatar : http://image.luosen365.com/head_1.png
             * nickname : fire
             * content : wobuzhidaofazhefjdsfsfsdjfsdalfj
             * fabulous : 0
             * location : 信阳市（默认）
             * createTime : 刚刚
             */

            private int commentId;
            private String username;
            private String avatar;
            private String nickname;
            private String content;
            private int fabulous;
            private String location;
            private String createTime;

            public static DataBean objectFromData(String str) {

                return new Gson().fromJson(str, DataBean.class);
            }

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getFabulous() {
                return fabulous;
            }

            public void setFabulous(int fabulous) {
                this.fabulous = fabulous;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
