package com.quickly.xqw.module.circle.cotent;

import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.module.base.IBasePresenter;
import com.quickly.xqw.module.base.IBaseView;

public interface CircleContentContract {

    interface View extends IBaseView<Presenter>{
        void onSetBean(ArticleBean.DataBean.ListBean bean);
    }

    interface Presenter extends IBasePresenter{

        /**
         * 请求数据
         */
        void doLoadData(ArticleBean.DataBean.ListBean bean);
    }

}
