package com.quickly.xqw.module.news;
import android.support.annotation.NonNull;

import com.quickly.xqw.ErrorAction;
import com.quickly.xqw.api.INewsApi;
import com.quickly.xqw.model.news.BannerBean;
import com.quickly.xqw.model.news.CategoryBean;
import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.utils.RetrofitFactory9001;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 分页从100页里随机加载
 */
public class NewsPresenter implements NewsContract.Presenter {

    private static final String TAG = "NewsPresenter";

    private NewsContract.View mView;

    private List mList = new ArrayList<>();

    private Random random = new Random();


    public NewsPresenter(NewsContract.View view) {
        this.mView = view;
    }

    @Override
    public void doInitData(String... args) {

    }

    @Override
    public void doRefresh(@NonNull RefreshLayout refreshLayout) {

        this.fetch().subscribe(list->{
            if(null != list && list.size() > 0) {
                mList.clear();
                this.addLayout();
                doSetAdapter(list);
                refreshLayout.finishRefresh();
                doShowSuccess();
            }else {
                refreshLayout.finishRefresh();
            }
        },throwable ->{
            ErrorAction.print(throwable);
            doShowError();
        });
    }

    @Override
    public void doLoadMoreData(@NonNull RefreshLayout refreshLayout) {
        this.fetch().subscribe(list->{
            if(null != list && list.size() > 0) {
                doSetAdapter(list);
                refreshLayout.finishLoadMore();
            }else {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
            doShowSuccess();
        },throwable ->{
            ErrorAction.print(throwable);
            doShowError();
        });
    }

    @Override
    public void doSetAdapter(List dataBean) {
        mList.addAll(dataBean);
        mView.onSetAdapter(mList);
    }

    @Override
    public void addLayout() {
        mList.add(new BannerBean());
        mList.add(new CategoryBean.DataBean.ListBean());
    }


    @Override
    public void doShowSuccess() {
        mView.onShowSuccess();
    }

    @Override
    public void doShowError() {
        mView.onShowNetError();
    }

    private ObservableSubscribeProxy<List<NewsArticleBean.DataBean.ListBean>> fetch(){
        int cursor = random.nextInt(100);
        return RetrofitFactory9001.getRetrofit().create(INewsApi.class).getRecommend(cursor)
                .map(resp->resp.getData().getList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(mView.bindAutoDispose());
    }
}
