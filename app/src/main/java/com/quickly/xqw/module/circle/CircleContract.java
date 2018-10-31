package com.quickly.xqw.module.circle;
import com.quickly.xqw.module.base2.IBaseListPresenter;
import com.quickly.xqw.module.base2.IBaseListView;

public interface CircleContract {

    interface View extends IBaseListView<CircleContract.Presenter> {
    }

    interface Presenter extends IBaseListPresenter {
    }

}
