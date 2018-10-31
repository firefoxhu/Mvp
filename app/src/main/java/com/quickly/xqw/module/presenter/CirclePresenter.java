package com.quickly.xqw.module.presenter;
import android.support.annotation.NonNull;

import com.quickly.xqw.api.ICircle;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.module.contract.CircleContract;
import com.quickly.xqw.utils.RetrofitFactory8080;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CirclePresenter implements CircleContract.Presenter {

    private static final String TAG = "CirclePresenter";

    private CircleContract.View view;

    private List dataList = new ArrayList<>();

    private int page = 0;

    public CirclePresenter(CircleContract.View view) {
        this.view = view;
    }
    @Override
    public void doInitData(String... args) {

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

    private ObservableSubscribeProxy<ArticleBean> requestData(){
        return RetrofitFactory8080.getRetrofit().create(ICircle.class).getArticle(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(view.bindAutoDispose());
    }
}
