package com.quickly.xqw.module.welfare.video;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.quickly.xqw.Register;
import com.quickly.xqw.api.HttpUri;
import com.quickly.xqw.module.base2.BaseSmartListFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class VideoUIFragment  extends BaseSmartListFragment<VideoContract.Presenter> implements VideoContract.View,OnRefreshLoadMoreListener {


    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }


    @Override
    protected void initData() {
        super.initData();
        this.presenter.doInitData(HttpUri.VIDEO_ENTERTAINMENT_ID);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected String getTitle() {
        return null;
    }


    @Override
    protected void registerAdapter() {
        Register.registerVideoList(mMultiTypeAdapter);
    }

    @Override
    public void setPresenter(VideoContract.Presenter presenter) {
        if(presenter == null) {
            this.presenter = new VideoPresenter(this);
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