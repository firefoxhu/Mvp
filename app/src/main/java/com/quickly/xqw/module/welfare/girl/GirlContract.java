package com.quickly.xqw.module.welfare.girl;

import com.quickly.xqw.module.base2.IBaseListPresenter;
import com.quickly.xqw.module.base2.IBaseListView;

public interface GirlContract {

    interface View extends IBaseListView<Presenter>{}

    interface Presenter extends IBaseListPresenter{}

}
