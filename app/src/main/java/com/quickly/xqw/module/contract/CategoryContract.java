package com.quickly.xqw.module.contract;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.List;

public interface CategoryContract {

    interface View{

        /**
         * 绑定生命周期
         */
        <X> AutoDisposeConverter<X> bindAutoDispose();

        /**
         * 显示网络错误
         */
        void onShowNetError();


        /**
         * 设置适配器
         */
        void onSetAdapter(List<?> list);
    }

    interface Presenter{

        /**
         * 请求数据
         * @param args
         */
        void doInitData(String...args);

        /**
         * 下拉刷新
         */
        void doRefresh(@NonNull RefreshLayout refreshLayout);

        /**
         * 再次发起请求
         */
        void doLoadMoreData(@NonNull RefreshLayout refreshLayout);

        /**
         * 设置适配器
         */
        void doSetAdapter(List dataBean);

    }


}
