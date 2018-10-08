package com.quickly.xqw.module.news.content;

import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.module.base.IBasePresenter;
import com.quickly.xqw.module.base.IBaseView;

public interface NewsContentContract {

    interface View extends IBaseView<Presenter>{
        /**
         * 加载网页
         */
        void onSetWebView(String url,boolean flag);
    }

    interface Presenter extends IBasePresenter{
        /**
         * 请求数据
         */
        void doLoadData(NewsArticleBean.DataBean.ListBean bean);
    }
}
