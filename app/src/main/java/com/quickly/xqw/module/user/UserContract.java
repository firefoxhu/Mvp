package com.quickly.xqw.module.user;

import com.quickly.xqw.module.base2.IBasePresenter;
import com.quickly.xqw.module.base2.IBaseView;

public interface UserContract {

    interface View extends IBaseView<Presenter>{}

    interface Presenter extends IBasePresenter{}

}
