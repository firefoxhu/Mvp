package com.quickly.xqw.model.circle;

import java.util.List;

public class CommentBean {


    /**
     * code : 0
     * msg : 请求成功！
     * timestamp : 1538309719
     * nonceStr : kDYERAn8
     * data : {"commentsNumber":1,"hasNext":false,"list":[{"commentId":445,"avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDribsIy35biavqDC2stYcwr2Dw/132","nickname":"秋高气傲～似逍遥","content":"好好好刚还会","fabulous":1,"location":"中国农业银行项店支行(信阳市息县)","createTime":"4分钟前"}]}
     */

    private int code;
    private String msg;
    private int timestamp;
    private String nonceStr;
    private DataBean data;

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
         * commentsNumber : 1
         * hasNext : false
         * list : [{"commentId":445,"avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDribsIy35biavqDC2stYcwr2Dw/132","nickname":"秋高气傲～似逍遥","content":"好好好刚还会","fabulous":1,"location":"中国农业银行项店支行(信阳市息县)","createTime":"4分钟前"}]
         */

        private int commentsNumber;
        private boolean hasNext;
        private List<ListBean> list;

        public int getCommentsNumber() {
            return commentsNumber;
        }

        public void setCommentsNumber(int commentsNumber) {
            this.commentsNumber = commentsNumber;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * commentId : 445
             * avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDribsIy35biavqDC2stYcwr2Dw/132
             * nickname : 秋高气傲～似逍遥
             * content : 好好好刚还会
             * fabulous : 1
             * location : 中国农业银行项店支行(信阳市息县)
             * createTime : 4分钟前
             */

            private int commentId;
            private String avatar;
            private String nickname;
            private String content;
            private int fabulous;
            private String location;
            private String createTime;

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
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
