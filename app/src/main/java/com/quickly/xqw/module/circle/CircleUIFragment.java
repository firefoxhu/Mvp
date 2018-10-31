package com.quickly.xqw.module.circle;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.quickly.xqw.Register;
import com.quickly.xqw.module.base2.BaseSmartListFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class CircleUIFragment extends BaseSmartListFragment<CircleContract.Presenter>  implements CircleContract.View, OnRefreshLoadMoreListener {

    private static final String TITLE = "圈子";

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mToolbar.setBackgroundColor(Color.parseColor("#F9BB71"));
        mTitle.setTextColor(Color.WHITE);

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
        Register.registerCircleArticle(mMultiTypeAdapter);
    }

    @Override
    public void setPresenter(CircleContract.Presenter presenter) {
        if(presenter == null) {
            this.presenter = new CirclePresenter(this);
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
        return true;
    }
}
