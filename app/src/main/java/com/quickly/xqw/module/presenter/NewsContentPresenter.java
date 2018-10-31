package com.quickly.xqw.module.presenter;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.quickly.xqw.ImageBrowserActivity;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.api.INewsApi;
import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.model.news.NewsContent;
import com.quickly.xqw.module.contract.NewsContentContract;
import com.quickly.xqw.utils.RetrofitFactory9001;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsContentPresenter implements NewsContentContract.Presenter {

    private static final String TAG = "NewsContentPresenter";
    // 获取img标签正则
    private static final String IMAGE_URL_REGEX = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
    private NewsContentContract.View view;
    private String html;


    public NewsContentPresenter(NewsContentContract.View view) {
        this.view = view;
    }

    @Override
    public void doLoadData(NewsArticleBean.DataBean.ListBean bean) {
        RetrofitFactory9001.getRetrofit().create(INewsApi.class).getNewsDetail(bean.getId())
                .map(data->getHTML(data.getData()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(view.bindAutoDispose())
                .subscribe(s -> view.onSetWebView(s,true),
                        throwable -> {
                            view.onSetWebView(null,false);
                            doShowNetError();
                        });
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }


    private String getHTML(NewsContent.DataBean bean) {
        String content = bean.getContent();
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
