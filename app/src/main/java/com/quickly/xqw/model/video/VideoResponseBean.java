package com.quickly.xqw.model.video;

import com.google.gson.Gson;

import java.util.List;

public class VideoResponseBean {


    private List<V9LG4CHORBean> V9LG4CHOR;

    public static VideoResponseBean objectFromData(String str) {

        return new Gson().fromJson(str, VideoResponseBean.class);
    }

    public List<V9LG4CHORBean> getV9LG4CHOR() {
        return V9LG4CHOR;
    }

    public void setV9LG4CHOR(List<V9LG4CHORBean> V9LG4CHOR) {
        this.V9LG4CHOR = V9LG4CHOR;
    }

    public static class V9LG4CHORBean {
        /**
         * sizeHD : 0
         * mp4Hd_url : null
         * description : 孟非调侃引廖凡不满 现场尴尬道歉
         * title : 孟非调侃引廖凡不满 尴尬道歉
         * mp4_url : http://flv3.bn.netease.com/videolib3/1403/03/AZOTv2735/SD/AZOTv2735-mobile.mp4
         * vid : V9LFM6GH7
         * cover : http://vimg1.ws.126.net/image/snapshot/2014/3/H/8/V9LFM6GH8.jpg
         * sizeSHD : 0
         * playersize : 0
         * ptime : 2014-03-03 10:13:42
         * m3u8_url : http://flv.bn.netease.com/videolib3/1403/03/AZOTv2735/SD/movie_index.m3u8
         * topicImg : http://vimg2.ws.126.net/image/snapshot/2012/11/I/3/V8GIAB8I3.jpg
         * votecount : 356
         * length : 210
         * videosource : 新媒体
         * m3u8Hd_url : null
         * sizeSD : 9135
         * topicSid : V8GIAB8I2
         * playCount : 880088
         * replyCount : 504
         * replyBoard : video_bbs
         * replyid : 9LFM6GH7008535RB
         * topicName : 03明星八卦
         * sectiontitle :
         * topicDesc : 原创娱乐圈八卦事件。
         */

        private int sizeHD;
        private Object mp4Hd_url;
        private String description;
        private String title;
        private String mp4_url;
        private String vid;
        private String cover;
        private int sizeSHD;
        private int playersize;
        private String ptime;
        private String m3u8_url;
        private String topicImg;
        private int votecount;
        private int length;
        private String videosource;
        private Object m3u8Hd_url;
        private int sizeSD;
        private String topicSid;
        private int playCount;
        private int replyCount;
        private String replyBoard;
        private String replyid;
        private String topicName;
        private String sectiontitle;
        private String topicDesc;

        public static V9LG4CHORBean objectFromData(String str) {

            return new Gson().fromJson(str, V9LG4CHORBean.class);
        }

        public int getSizeHD() {
            return sizeHD;
        }

        public void setSizeHD(int sizeHD) {
            this.sizeHD = sizeHD;
        }

        public Object getMp4Hd_url() {
            return mp4Hd_url;
        }

        public void setMp4Hd_url(Object mp4Hd_url) {
            this.mp4Hd_url = mp4Hd_url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMp4_url() {
            return mp4_url;
        }

        public void setMp4_url(String mp4_url) {
            this.mp4_url = mp4_url;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getSizeSHD() {
            return sizeSHD;
        }

        public void setSizeSHD(int sizeSHD) {
            this.sizeSHD = sizeSHD;
        }

        public int getPlayersize() {
            return playersize;
        }

        public void setPlayersize(int playersize) {
            this.playersize = playersize;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getM3u8_url() {
            return m3u8_url;
        }

        public void setM3u8_url(String m3u8_url) {
            this.m3u8_url = m3u8_url;
        }

        public String getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(String topicImg) {
            this.topicImg = topicImg;
        }

        public int getVotecount() {
            return votecount;
        }

        public void setVotecount(int votecount) {
            this.votecount = votecount;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getVideosource() {
            return videosource;
        }

        public void setVideosource(String videosource) {
            this.videosource = videosource;
        }

        public Object getM3u8Hd_url() {
            return m3u8Hd_url;
        }

        public void setM3u8Hd_url(Object m3u8Hd_url) {
            this.m3u8Hd_url = m3u8Hd_url;
        }

        public int getSizeSD() {
            return sizeSD;
        }

        public void setSizeSD(int sizeSD) {
            this.sizeSD = sizeSD;
        }

        public String getTopicSid() {
            return topicSid;
        }

        public void setTopicSid(String topicSid) {
            this.topicSid = topicSid;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getReplyBoard() {
            return replyBoard;
        }

        public void setReplyBoard(String replyBoard) {
            this.replyBoard = replyBoard;
        }

        public String getReplyid() {
            return replyid;
        }

        public void setReplyid(String replyid) {
            this.replyid = replyid;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getSectiontitle() {
            return sectiontitle;
        }

        public void setSectiontitle(String sectiontitle) {
            this.sectiontitle = sectiontitle;
        }

        public String getTopicDesc() {
            return topicDesc;
        }

        public void setTopicDesc(String topicDesc) {
            this.topicDesc = topicDesc;
        }
    }
}
