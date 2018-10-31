package com.quickly.xqw.module.news.detail;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.quickly.xqw.ImageBrowserActivity;
import com.quickly.xqw.XQWApplication;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsDetailPresenter implements NewsDetailContract.Presenter {

    private static final String TAG = "NewsDetailPresenter";

    private static final String IMAGE_URL_REGEX = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";

    private final NewsDetailContract.View mView;

    private String html;

    public NewsDetailPresenter(NewsDetailContract.View view) {
        mView = view;
    }

    @Override
    public void doInitData(String... args) {
        this.getHTML(args[0]);
        this.mView.onSetWebView(html);


    }

    @Override
    public void doShowSuccess() {
        mView.onShowSuccess();
    }

    @Override
    public void doShowError() {
        mView.onShowNetError();
    }


    private String getHTML(String content) {
        if (content != null) {


            html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">" +
                    "<body>\n" +
                    "<article class=\"article-container\">\n" +
                    "    <div class=\"article__content article-content\">" +
                    content +
                    "    </div>\n" +
                    "</article>\n" +
                    "</body>\n" +
                    "</html>";

            return html;
        } else {
            return null;
        }
    }


    /**
     * js 通信接口，定义供 JavaScript 调用的交互接口
     * 点击图片启动新的 Activity，并传入点击图片对应的 url 和页面所有图片
     * 对应的 url
     *
     * @param url 点击图片对应的 url
     */
    @JavascriptInterface
    public void openImage(String url) {
        if (!TextUtils.isEmpty(url)) {
            ArrayList<String> list = getAllImageUrlFromHtml(html);
            if (list.size() > 0) {
                ImageBrowserActivity.start(XQWApplication.appContext, url, list);
                Log.d(TAG, "openImage: " + list.toString());
            }
        }
    }

    /***
     * 获取页面所有图片对应的地址对象，
     * 例如 <img src="http://sc1.hao123img.com/data/f44d0aab7bc35b8767de3c48706d429e" />
     */
    private ArrayList<String> getAllImageUrlFromHtml(String html) {
        Matcher matcher = Pattern.compile(IMAGE_URL_REGEX).matcher(html);
        ArrayList<String> imgUrlList = new ArrayList<>();
        while (matcher.find()) {
            int count = matcher.groupCount();
            if (count >= 1) {
                imgUrlList.add(matcher.group(1));
            }
        }
        return imgUrlList;
    }
}
