package com.quickly.xqw.module.news;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.quickly.xqw.R;
import com.quickly.xqw.Register;
import com.quickly.xqw.module.base2.BaseSmartListFragment;
import com.quickly.xqw.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class NewsUIFragment extends BaseSmartListFragment<NewsContract.Presenter> implements NewsContract.View, OnRefreshLoadMoreListener {

    private static final String TAG = "CircleViewTest";

    private static final String TITLE = "首页";


    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mToolbar.setBackgroundColor(Color.parseColor("#DF5A55"));
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
        Register.registerNewsArticleItem(mMultiTypeAdapter);
    }


    @Override
    protected boolean hasToolbar() {
        return true;
    }


    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        if(presenter == null) {
            this.presenter = new NewsPresenter(this);
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
}
