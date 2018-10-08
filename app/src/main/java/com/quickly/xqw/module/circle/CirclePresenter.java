package com.quickly.xqw.module.circle;
import com.quickly.xqw.api.HttpUri;
import com.quickly.xqw.api.ICircle;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.module.circle.CircleContract.Presenter;
import com.quickly.xqw.utils.RetrofitFactory;
import com.quickly.xqw.utils.RetrofitFactory2;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CirclePresenter implements Presenter {

    private static final String TAG = "CirclePresenter";

    private CircleContract.View view;

    private List dataList = new ArrayList();

    //  初始化页面
    private int page = 0;


    public CirclePresenter(CircleContract.View view) {
        this.view = view;
    }

    @Override
    public void doLoadData(String... args) {

        RetrofitFactory2.getRetrofit(HttpUri.SERVER_URL).create(ICircle.class).getArticle(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(view.bindAutoDispose())
                .subscribe(resp->{
                    if(null != resp && resp.getData().getList().size() > 0) {
                        doSetAdapter(resp.getData().getList());
                    }else {
                        doShowNoMore();
                    }
            },throwable -> doShowNetError());


    }

    @Override
    public void doLoadMoreData() {
        page++;
        doLoadData();
    }

    @Override
    public void doSetAdapter(List<ArticleBean.DataBean.ListBean> dataBean) {
        dataList.addAll(dataBean);
        view.onSetAdapter(dataList);
        view.onHideLoading();
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
        view.onShowNoMore();
    }

    @Override
    public void doRefresh() {
        page = 0;

        if(dataList.size() > 0 ) {
            dataList.clear();
        }
        view.onShowLoading();
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }
}
