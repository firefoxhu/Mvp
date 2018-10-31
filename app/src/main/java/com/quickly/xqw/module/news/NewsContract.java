package com.quickly.xqw.module.news;
import com.quickly.xqw.module.base2.IBaseListPresenter;
import com.quickly.xqw.module.base2.IBaseListView;

public interface NewsContract {

    interface View extends IBaseListView<Presenter>{
    }

    interface Presenter extends IBaseListPresenter {
    }

}
