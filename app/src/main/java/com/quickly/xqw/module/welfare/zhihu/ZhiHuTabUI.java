package com.quickly.xqw.module.welfare.zhihu;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quickly.xqw.Register;
import com.quickly.xqw.module.base2.BaseSmartListFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class ZhiHuTabUI extends BaseSmartListFragment<ZhiHuContract.Presenter> implements ZhiHuContract.View,OnRefreshLoadMoreListener {

    private static final String TITLE = "知乎";

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
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
        Register.registerZhiHu(mMultiTypeAdapter);
    }

    @Override
    public void setPresenter(ZhiHuContract.Presenter presenter) {
        if(presenter == null) {
            this.presenter =new ZhiHuPresenter(this);
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
