package com.quickly.xqw.module.ui;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.quickly.xqw.R;
import com.quickly.xqw.Register;
import com.quickly.xqw.module.base.BaseSmartFragment;
import com.quickly.xqw.module.contract.ZhiHuContact;
import com.quickly.xqw.module.presenter.ZhiHuPresenter;
import com.quickly.xqw.utils.DiffCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class ZhiHuTab  extends  BaseSmartFragment<ZhiHuContact.Presenter> implements ZhiHuContact.View,OnLoadMoreListener {

    private SmartRefreshLayout mSmartRefreshLayout;

    private RecyclerView mRecyclerView;

    private MultiTypeAdapter adapter;

    private Items oldItems = new Items();

    @Override
    protected int attachLayoutId() {
        return R.layout.item_tab_list;
    }

    @Override
    protected void initView(View view) {
        mSmartRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() throws NullPointerException {
        this.mSmartRefreshLayout.setOnLoadMoreListener(this);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerZhiHu(adapter);
        mRecyclerView.setAdapter(adapter);

        mSmartRefreshLayout.autoLoadMore(0);
    }

    @Override
    protected void setPresenter(ZhiHuContact.Presenter presenter) {
        if(presenter == null) {
            this.presenter = new ZhiHuPresenter(this);
        }
    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems  = new Items(list);
        DiffCallback.create(oldItems,newItems,adapter);
        oldItems.clear();
        oldItems.addAll(newItems);

        // 停止滑动
        mRecyclerView.stopScroll();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        this.presenter.doLoadMoreData(refreshLayout);
    }

    /**
     * 绑定生命周期
     */
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
