package com.quickly.xqw.module.circle.cotent;

import com.quickly.xqw.model.circle.ArticleBean;

public class CircleContentPresenter implements CircleContentContract.Presenter {

    private static final String TAG ="CircleContentPresenter";
    private final CircleContentContract.View view;

    public CircleContentPresenter(CircleContentContract.View view) {
        this.view = view;
    }


    @Override
    public void doLoadData(ArticleBean.DataBean.ListBean bean) {
        if(bean == null){
            view.onHideLoading();
            view.onShowNetError();
        }
        view.onSetBean(bean);
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }
}
