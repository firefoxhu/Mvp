package com.quickly.xqw.module.presenter;

import android.support.annotation.NonNull;

import com.quickly.xqw.api.INewsApi;
import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.module.contract.CategoryContract;
import com.quickly.xqw.utils.RetrofitFactory9001;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CategoryNewsPresenter implements CategoryContract.Presenter {


    private CategoryContract.View view;

    private int page = 0;

    private String categoryId = null;

    private List dataList = new ArrayList<>();


    public CategoryNewsPresenter(CategoryContract.View view) {
        this.view = view;
    }



    @Override
    public void doInitData(String... args) {
        this.categoryId = args[0];
    }

    @Override
    public void doRefresh(@NonNull RefreshLayout refreshLayout) {
        this.page = 0;
        this.requestData().subscribe(resp->{
            this.dataList.clear();
            if(null != resp && resp.getData().getList().size() > 0) {
                doSetAdapter(resp.getData().getList());
                refreshLayout.finishRefresh();
            }else {
                refreshLayout.finishRefresh();
            }
        },throwable -> view.onShowNetError());
    }

    @Override
    public void doLoadMoreData(@NonNull RefreshLayout refreshLayout) {
        this.page++;
        this.requestData().subscribe(resp->{
            if(null != resp && resp.getData().getList().size() > 0) {
                doSetAdapter(resp.getData().getList());
                refreshLayout.finishLoadMore();
            }else {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        },throwable -> view.onShowNetError());
    }

    @Override
    public void doSetAdapter(List dataBean) {
       this.dataList.addAll(dataBean);
       this.view.onSetAdapter(this.dataList);
    }

    private ObservableSubscribeProxy<NewsArticleBean> requestData(){
       return RetrofitFactory9001.getRetrofit().create(INewsApi.class).getNewsByCategory(page, categoryId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(view.bindAutoDispose());
    }


}
