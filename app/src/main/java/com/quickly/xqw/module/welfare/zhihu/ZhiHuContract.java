package com.quickly.xqw.module.welfare.zhihu;
import com.quickly.xqw.module.base2.IBaseListPresenter;
import com.quickly.xqw.module.base2.IBaseListView;

public class ZhiHuContract {

    interface View extends IBaseListView<ZhiHuContract.Presenter> {}

    interface Presenter extends IBaseListPresenter {}
}
