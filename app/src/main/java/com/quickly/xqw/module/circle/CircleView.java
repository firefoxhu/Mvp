package com.quickly.xqw.module.circle;
import android.util.Log;
import android.view.View;

import com.quickly.xqw.Register;
import com.quickly.xqw.model.LoadingBean;
import com.quickly.xqw.module.base.BaseListFragment;
import com.quickly.xqw.utils.DiffCallback;
import com.quickly.xqw.utils.OnLoadMoreListener;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class CircleView  extends BaseListFragment<CircleContract.Presenter> implements CircleContract.View{

    private static final String TAG = "CircleView";

    public static   CircleView newInstance(){
        return new CircleView();
    }


    @Override
    protected void initData() throws NullPointerException {
        this.onLoadData();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Log.i(TAG, "initView: ");
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerCircleArticle(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if(canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMoreData();
                }
            }
        });
    }

    @Override
    public void fetchData() {
        super.fetchData();
        onLoadData();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData();
    }

    @Override
    public void onSetAdapter(List<?> list) {
        Log.i(TAG, "onSetAdapter: ");
        Items newItems  = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(oldItems,newItems,adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;

        /**
         * https://medium.com/@hanru.yeh/recyclerview-and-appbarlayout-behavior-changed-in-v26-0-x-d9eb4de78fc0
         * support libraries v26 增加了 RV 惯性滑动，当 root layout 使用了 AppBarLayout Behavior 就会自动生效
         * 因此需要手动停止滑动
         */
        recyclerView.stopScroll();
    }

    @Override
    public void setPresenter(CircleContract.Presenter presenter) {
        if(presenter == null) {
            this.presenter = new CirclePresenter(this);
        }
    }
}