package com.quickly.xqw.module.contract;

import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.module.base.IBaseListView;
import com.quickly.xqw.module.base.IBasePresenter;

import java.util.List;

public interface NewsContract {

    interface View extends IBaseListView<Presenter>{

        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();

    }
    interface Presenter extends IBasePresenter{

        /**
         * 请求数据
         * @param args
         */
        void doLoadData(String...args);

        /**
         * 再次发起请求
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<NewsArticleBean.DataBean.ListBean> dataBean);

        /**
         * 加载完毕
         */
        void doShowNoMore();

    }

}
