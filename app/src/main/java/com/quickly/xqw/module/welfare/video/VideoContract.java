package com.quickly.xqw.module.welfare.video;
import com.quickly.xqw.module.base2.IBaseListPresenter;
import com.quickly.xqw.module.base2.IBaseListView;

public interface VideoContract {


    interface View extends IBaseListView<VideoContract.Presenter> {
    }

    interface Presenter extends IBaseListPresenter {
    }



}
