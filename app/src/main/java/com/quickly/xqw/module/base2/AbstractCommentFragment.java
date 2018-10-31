package com.quickly.xqw.module.base2;
public abstract class AbstractCommentFragment<T extends IBasePresenter> extends CommonFragment<T> implements IBaseListView<T>{

    @Override
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    public void onShowSuccess() {
        this.presenter.doShowSuccess();
    }

    @Override
    public void onShowNetError() {
        this.presenter.doShowError();
    }

}
