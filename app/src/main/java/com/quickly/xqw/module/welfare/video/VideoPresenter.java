package com.quickly.xqw.module.welfare.video;
import android.support.annotation.NonNull;

import com.quickly.xqw.ErrorAction;
import com.quickly.xqw.api.HostType;
import com.quickly.xqw.api.VideoApi;
import com.quickly.xqw.model.video.VideoResponseBean;
import com.quickly.xqw.utils.RetrofitManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VideoPresenter implements VideoContract.Presenter{

    private List  mList = new ArrayList();

    private final VideoContract.View mView;

    private String mType = "";

    private int startPage = 0;

    public VideoPresenter(VideoContract.View view) {
        mView = view;
    }


    @Override
    public void doRefresh(@NonNull RefreshLayout refreshLayout) {
        this.startPage = 0;
        this.getVideosListData().subscribe(list->{
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
        ++this.startPage;
        this.getVideosListData().subscribe(list->{
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

    }

    @Override
    public void doInitData(String... args) {
        this.mType = args[0];
    }

    @Override
    public void doShowSuccess() {
        mView.onShowSuccess();
    }

    @Override
    public void doShowError() {
        mView.onShowNetError();
    }


    public ObservableSubscribeProxy<List<VideoResponseBean.V9LG4CHORBean>> getVideosListData() {
        return RetrofitManager.getDefault(HostType.HOST_WANGYI).retrofit.create(VideoApi.class).getVideoList(RetrofitManager.getCacheControl(),mType,startPage)
                .map(resp->resp.getV9LG4CHOR())
                .distinct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(mView.bindAutoDispose());
    }
}
