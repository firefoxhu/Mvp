package com.quickly.xqw.api;

public interface HttpUri {
    /**
    * 方便我们切换测试服务器与主站服务器的地址
    */
    String SERVER_IP = "http://39.107.228.75:9001/";

    String SERVER_IP_8080 = "https://www.luosen365.com/";

    String SERVER_GIRL = "https://gank.io/api/";

    String  SERVER_WANGYI = "http://c.m.163.com/";

    String SERVER_ZHIHU = "http://news-at.zhihu.com/";


    String CATEGORY_LIST =  "/category";
    String RECOMMEND_LIST =   "/news/recommend";
    String NEWS_DETAIL =  "/news/detail";

    String NEWS_COMMENT =  "/comment/list";

    /**
     * 项目app
     */
    String CIRCLE_ARTICLE = "/article/list";

    String CIRCLE_ARTICLE_WRITE = "write"; // 需要权限验证

    String CIRCLE_ARTICLE_FABULOUS = "/article/fabulous";
    String CIRCLE_ARTICLE_UNFABULOUS = "/article/unfabulous";
    String CIRCLE_ARTICLE_VIEWS = "/article/views";

    // 评论 获取文章评论列表
    String CIRCLE_ARTICLE_COMMENT_LIST = "/comment/list"; //  articleId   page
    // 评论文章
    String CIRCLE_ARTICLE_COMMENT = "/comment/write"; // articleId content 128

    String  CIRCLE_ARTICLE_FABULOUS_LSIT = "/fabulous";

    String  CIRCLE_ARTICLE_FABULOUS_ZAN = "/fabulous";

    String  CIRCLE_ARTICLE_FABULOUS_UN_ZAN = "/fabulous";






    // HEADER 设置权限密钥
    String CIRCLE_OWNER_ARTICLE = "/article/listOwner";


    String CIRCLE_VIDEO =  "/video/list";

    /**
     * 用户登录
     */
    String MOBILE_SEND = "/code/smsCode"; // mobile

    String MOBILE_VALIDATE = "/authentication/mobile";

    String USER_LOGIN ="/app/login";


    /**
     * 验证码
     */

    String  CODE_IMAGE_FETCH = "/code/image?height=25&width=60&timer="+ System.currentTimeMillis();





    /** *******************************************************************************************************************************************
     * {"code":0,"msg":"处理成功！","data":{"hasNext":false,"list":[
     * {"id":"288795c262794776aa8982f840b183b1","title":"空调","imgUrl":"http://www.luosen365.com/image/20180516/1526462666513.gif","num":"0"},
     * {"id":"288795c262794776aa8982f840b183b1","title":"油烟机","imgUrl":"http://www.luosen365.com/image/20180516/1526462622687.gif","num":"0"},
     * {"id":"8c1a026392c64dc1bb12138ae1a96eb2","title":"热水器","imgUrl":"http://www.luosen365.com/image/20180516/1526462313535.gif","num":"0"},
     * {"id":"d31810b74135459d9a1fc3e084d33fce","title":"洗衣机","imgUrl":"http://www.luosen365.com/image/20180516/1526462692656.gif","num":"0"},
     * {"id":"da12bb48aa434bec8af024f50f5169b9","title":"其它","imgUrl":"http://www.luosen365.com/image/20180516/1526462719858.gif","num":"0"}]}}
     ** ********************************************************************************************************************************************
     */
    public static String[] VIDEO_TYPE = {
            "288795c262794776aa8982f840b183b1","288795c262794776aa8982f840b183b1","8c1a026392c64dc1bb12138ae1a96eb2","d31810b74135459d9a1fc3e084d33fce","da12bb48aa434bec8af024f50f5169b9"
    };


    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.HOST_8080:
                host = SERVER_IP_8080;
                break;
            case HostType.HOST_9001:
                host = SERVER_IP;
                break;
            case HostType.HOST_GIRL:
                host = SERVER_GIRL;
                break;
            case HostType.HOST_ZHIHU:
                host = SERVER_ZHIHU;
                break;
            case HostType.HOST_WANGYI:
                host = SERVER_WANGYI;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }

    /**
     * 视频 http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/10-10.html
     */
    String Video = "nc/video/list/";
    String VIDEO_CENTER = "/n/";
    String VIDEO_END_URL = "-10.html";
    // 热点视频
    String VIDEO_HOT_ID = "V9LG4B3A0";
    // 娱乐视频
    String VIDEO_ENTERTAINMENT_ID = "V9LG4CHOR";
    // 搞笑视频
    String VIDEO_FUN_ID = "V9LG4E6VR";
    // 精品视频
    String VIDEO_CHOICE_ID = "00850FRB";

}