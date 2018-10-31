package com.quickly.xqw.module.base2;
import android.support.annotation.NonNull;

import com.quickly.xqw.model.news.NewsArticleBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;

import java.util.List;

public interface IBaseListPresenter extends IBasePresenter{


    /**
     * 下拉刷新
     */
    void doRefresh(@NonNull RefreshLayout refreshLayout);

    /**
     * 再次发起请求
     */
    void doLoadMoreData(@NonNull RefreshLayout refreshLayout);

    /**
     * 设置适配器
     */
    void doSetAdapter(List dataBean);

    /**
     * 添加其他特殊布局
     */
    void addLayout();

}
