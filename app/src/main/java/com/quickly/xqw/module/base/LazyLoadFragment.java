package com.quickly.xqw.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Meiji on 2016/12/27.
 */

public abstract class LazyLoadFragment<T extends IBasePresenter> extends BaseFragment<T> {
    
    private static final String TAG = "LazyLoadFragment";

    /**
     * 是否初始化过布局
     */
    protected boolean isViewInitiated;
    /**
     * 当前界面是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 是否加载过数据
     */
    protected boolean isDataInitiated;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.i(TAG, "setUserVisibleHint: ");
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            prepareFetchData();
        }
    }

    /**
     * 懒加载
     */
    public abstract void fetchData();

    public void prepareFetchData() {
        Log.i(TAG, "prepareFetchData: ");
        prepareFetchData(false);
    }

    /**
     * 判断懒加载条件
     *
     * @param forceUpdate 强制更新，好像没什么用？
     */
    public void prepareFetchData(boolean forceUpdate) {
        Log.i(TAG, "prepareFetchData: ");
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
        }
    }
}