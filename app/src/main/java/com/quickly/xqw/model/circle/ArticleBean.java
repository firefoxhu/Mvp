package com.quickly.xqw.model.circle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.quickly.xqw.model.news.NewsArticleBean;

import java.io.Serializable;
import java.util.List;

public class ArticleBean implements Serializable{


    /**
     * code : 0
     * msg : 请求成功！
     * timestamp : 1536741971
     * nonceStr : 1A8U8VBh
     * data : {"hasNext":true,"list":[{"articleId":441,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"哈哈哈哈哈还哈哈哈哈","pictures":["http://image.luosen365.com/45fe541ca7be4180b14521ab16b69cc1.jpg","http://image.luosen365.com/262694864af649bf87c4d6a535b081e5.jpg"],"location":"信阳市（默认）","views":1,"fabulous":1,"commentsNumber":0,"createTime":"2018-08-29 18:52:05","comments":null},{"articleId":439,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"哈哈KKKKKKKKK","pictures":["http://image.luosen365.com/6931acf2df414e97b4b475d59fe7e725.jpg"],"location":"信阳市（默认）","views":0,"fabulous":1,"commentsNumber":0,"createTime":"2018-08-29 13:48:43","comments":null},{"articleId":437,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"大傻逼一个肾XP一爪子","pictures":["http://image.luosen365.com/d7d8887e28624b32adb86ee43991bab9.jpg"],"location":"雕琢时光(西亚店)","views":1,"fabulous":0,"commentsNumber":0,"createTime":"2018-08-25 16:56:44","comments":null},{"articleId":435,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"大傻逼一个人哈","pictures":["http://image.luosen365.com/8d2242803050461ca973d3bdacf3c7e0.jpg"],"location":"信阳市（默认）","views":0,"fabulous":0,"commentsNumber":0,"createTime":"2018-08-25 16:56:02","comments":null},{"articleId":432,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"用肉在让肉在真肉在","pictures":["http://image.luosen365.com/ef7b7ab3f55c49d1958dbf870c1996aa.jpg"],"location":"信阳市（默认）","views":8,"fabulous":1,"commentsNumber":1,"createTime":"2018-08-14 16:01:16","comments":null},{"articleId":430,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"纸箱子一直一直","pictures":["http://image.luosen365.com/af676270831445619810888b8683ea7f.jpg"],"location":"信阳市（默认）","views":2,"fabulous":1,"commentsNumber":0,"createTime":"2018-08-14 16:00:29","comments":null},{"articleId":418,"author":"古月","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLsib7FicVN9em77FicKqCMPU1nKM0CM62jicszBfVCZU0uk6icDHFTy6KQtceWoxGu0iccjltribicSR1V6w/132","top":"0","content":"范德萨发大水发打算发多少发","pictures":null,"location":"信阳市（默认）","views":5,"fabulous":2,"commentsNumber":0,"createTime":"2018-08-14 11:39:28","comments":null},{"articleId":416,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"咿呀咿呀哟玩游戏","pictures":["http://image.luosen365.com/7e42569bfadc4065a76a4c3a668ec510.jpg","http://image.luosen365.com/834ce5e4e2f14f65a131a8bc7f793d26.jpg","http://image.luosen365.com/d8f3e60a938e44008fd4fee9de3a1178.jpg","http://image.luosen365.com/e5bd87f127de46749d3e1ed67f4120e4.jpg","http://image.luosen365.com/4778ef84581541c692a1867a3705c0cb.jpg","http://image.luosen365.com/4fcd45994e4a45189c402c8d81733d90.jpg","http://image.luosen365.com/87a8ac472a8145d48ba6878fdc6c983a.jpg","http://image.luosen365.com/048821860d634495b78d356a7e815ac6.jpg"],"location":"信阳市（默认）","views":4,"fabulous":0,"commentsNumber":0,"createTime":"2018-08-14 00:54:29","comments":null}]}
     */

    private int code;
    private String msg;
    private int timestamp;
    private String nonceStr;
    private DataBean data;

    public static ArticleBean objectFromData(String str) {

        return new Gson().fromJson(str, ArticleBean.class);
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
         * hasNext : true
         * list : [{"articleId":441,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"哈哈哈哈哈还哈哈哈哈","pictures":["http://image.luosen365.com/45fe541ca7be4180b14521ab16b69cc1.jpg","http://image.luosen365.com/262694864af649bf87c4d6a535b081e5.jpg"],"location":"信阳市（默认）","views":1,"fabulous":1,"commentsNumber":0,"createTime":"2018-08-29 18:52:05","comments":null},{"articleId":439,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"哈哈KKKKKKKKK","pictures":["http://image.luosen365.com/6931acf2df414e97b4b475d59fe7e725.jpg"],"location":"信阳市（默认）","views":0,"fabulous":1,"commentsNumber":0,"createTime":"2018-08-29 13:48:43","comments":null},{"articleId":437,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"大傻逼一个肾XP一爪子","pictures":["http://image.luosen365.com/d7d8887e28624b32adb86ee43991bab9.jpg"],"location":"雕琢时光(西亚店)","views":1,"fabulous":0,"commentsNumber":0,"createTime":"2018-08-25 16:56:44","comments":null},{"articleId":435,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"大傻逼一个人哈","pictures":["http://image.luosen365.com/8d2242803050461ca973d3bdacf3c7e0.jpg"],"location":"信阳市（默认）","views":0,"fabulous":0,"commentsNumber":0,"createTime":"2018-08-25 16:56:02","comments":null},{"articleId":432,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"用肉在让肉在真肉在","pictures":["http://image.luosen365.com/ef7b7ab3f55c49d1958dbf870c1996aa.jpg"],"location":"信阳市（默认）","views":8,"fabulous":1,"commentsNumber":1,"createTime":"2018-08-14 16:01:16","comments":null},{"articleId":430,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"纸箱子一直一直","pictures":["http://image.luosen365.com/af676270831445619810888b8683ea7f.jpg"],"location":"信阳市（默认）","views":2,"fabulous":1,"commentsNumber":0,"createTime":"2018-08-14 16:00:29","comments":null},{"articleId":418,"author":"古月","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLsib7FicVN9em77FicKqCMPU1nKM0CM62jicszBfVCZU0uk6icDHFTy6KQtceWoxGu0iccjltribicSR1V6w/132","top":"0","content":"范德萨发大水发打算发多少发","pictures":null,"location":"信阳市（默认）","views":5,"fabulous":2,"commentsNumber":0,"createTime":"2018-08-14 11:39:28","comments":null},{"articleId":416,"author":"夏风爽起~心飞扬","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132","top":"0","content":"咿呀咿呀哟玩游戏","pictures":["http://image.luosen365.com/7e42569bfadc4065a76a4c3a668ec510.jpg","http://image.luosen365.com/834ce5e4e2f14f65a131a8bc7f793d26.jpg","http://image.luosen365.com/d8f3e60a938e44008fd4fee9de3a1178.jpg","http://image.luosen365.com/e5bd87f127de46749d3e1ed67f4120e4.jpg","http://image.luosen365.com/4778ef84581541c692a1867a3705c0cb.jpg","http://image.luosen365.com/4fcd45994e4a45189c402c8d81733d90.jpg","http://image.luosen365.com/87a8ac472a8145d48ba6878fdc6c983a.jpg","http://image.luosen365.com/048821860d634495b78d356a7e815ac6.jpg"],"location":"信阳市（默认）","views":4,"fabulous":0,"commentsNumber":0,"createTime":"2018-08-14 00:54:29","comments":null}]
         */

        private boolean hasNext;
        private List<ListBean> list;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<ArticleBean.DataBean.ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * articleId : 441
             * author : 夏风爽起~心飞扬
             * avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLke9yyicW4DA7TicbAt7qwI86iaXIn4I9a6j4JicUsFwXRyMVTDdbiabvDrhXrZs4bgF9hFJquIibw3jkA/132
             * top : 0
             * content : 哈哈哈哈哈还哈哈哈哈
             * pictures : ["http://image.luosen365.com/45fe541ca7be4180b14521ab16b69cc1.jpg","http://image.luosen365.com/262694864af649bf87c4d6a535b081e5.jpg"]
             * location : 信阳市（默认）
             * views : 1
             * fabulous : 1
             * commentsNumber : 0
             * createTime : 2018-08-29 18:52:05
             * comments : null
             */

            private int articleId;
            private String author;
            private String avatar;
            private String top;
            private String content;
            private String location;
            private int views;
            private int fabulous;
            private int commentsNumber;
            private String createTime;
            private String comments;
            private List<String> pictures;

            public static ListBean objectFromData(String str) {

                return new Gson().fromJson(str, ListBean.class);
            }

            public int getArticleId() {
                return articleId;
            }

            public void setArticleId(int articleId) {
                this.articleId = articleId;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getTop() {
                return top;
            }

            public void setTop(String top) {
                this.top = top;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public int getFabulous() {
                return fabulous;
            }

            public void setFabulous(int fabulous) {
                this.fabulous = fabulous;
            }

            public int getCommentsNumber() {
                return commentsNumber;
            }

            public void setCommentsNumber(int commentsNumber) {
                this.commentsNumber = commentsNumber;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public List<String> getPictures() {
                return pictures;
            }

            public void setPictures(List<String> pictures) {
                this.pictures = pictures;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.articleId);
                dest.writeString(this.author);
                dest.writeString(this.avatar);
                dest.writeString(this.top);
                dest.writeString(this.content);
                dest.writeString(this.location);
                dest.writeInt(this.views);
                dest.writeInt(this.fabulous);
                dest.writeInt(this.commentsNumber);
                dest.writeString(this.createTime);
                dest.writeString(this.comments);
                dest.writeStringList(this.pictures);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.articleId = in.readInt();
                this.author = in.readString();
                this.avatar = in.readString();
                this.top = in.readString();
                this.content = in.readString();
                this.location = in.readString();
                this.views = in.readInt();
                this.fabulous = in.readInt();
                this.commentsNumber = in.readInt();
                this.createTime = in.readString();
                this.comments = in.readParcelable(Object.class.getClassLoader());
                this.pictures = in.createStringArrayList();
            }

            public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel source) {
                    return new ListBean(source);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };
        }
    }
}
