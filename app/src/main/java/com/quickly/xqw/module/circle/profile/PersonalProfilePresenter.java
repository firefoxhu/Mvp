package com.quickly.xqw.module.circle.profile;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

public class PersonalProfilePresenter implements PersonalProfileContract.Presenter{

    private final PersonalProfileContract.View mView;

    public PersonalProfilePresenter(PersonalProfileContract.View view) {
        mView = view;
    }


    @Override
    public void doInitData(String... args) {

    }

    @Override
    public void doRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(1000);
    }

    @Override
    public void doLoadMoreData(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void doSetAdapter(List dataBean) {

    }

    @Override
    public void addLayout() {

    }

    @Override
    public void doShowSuccess() {
        this.mView.onShowSuccess();
    }

    @Override
    public void doShowError() {
        this.mView.onShowNetError();
    }
}
