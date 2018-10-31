package com.quickly.xqw.module.circle.profile;
import com.quickly.xqw.module.base2.IBaseListPresenter;
import com.quickly.xqw.module.base2.IBaseListView;

public interface PersonalProfileContract {


    interface View extends IBaseListView<PersonalProfileContract.Presenter> {
    }

    interface Presenter extends IBaseListPresenter {
    }

}
