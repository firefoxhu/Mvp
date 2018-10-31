package com.quickly.xqw.module.news.detail;
import com.quickly.xqw.module.base2.IBasePresenter;
import com.quickly.xqw.module.base2.IBaseView;

public interface NewsDetailContract {

    interface View extends IBaseView<Presenter> {

        void onSetWebView(String url);

        void initWebClient();
    }


    interface Presenter extends IBasePresenter {
    }



}
