package com.quickly.xqw.module.welfare.girl;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.quickly.xqw.Register;
import com.quickly.xqw.module.base2.BaseSmartListFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class GirlTabUI extends BaseSmartListFragment<GirlContract.Presenter> implements GirlContract.View,OnRefreshLoadMoreListener{

    private static final String TITLE = "美女";

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected String getTitle() {
        return TITLE;
    }

    @Override
    protected void registerAdapter() {
        Register.registerGirl(mMultiTypeAdapter);
    }

    @Override
    public void setPresenter(GirlContract.Presenter presenter) {
        if(presenter == null) {
            this.presenter = new GirlPresenter(this);
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        this.presenter.doLoadMoreData(refreshLayout);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        this.presenter.doRefresh(refreshLayout);
    }

    @Override
    protected boolean hasToolbar() {
        return false;
    }
}