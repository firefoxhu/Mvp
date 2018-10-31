package com.quickly.xqw.module.presenter;
import android.util.Log;

import com.quickly.xqw.api.INewsApi;
import com.quickly.xqw.model.news.BannerBean;
import com.quickly.xqw.model.news.CategoryBean;
import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.module.contract.NewsContract;
import com.quickly.xqw.utils.RetrofitFactory9001;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsArticlePresenter implements NewsContract.Presenter{


    private static final String TAG = "NewsArticlePresenter";

    private NewsContract.View view;

    private List dataList = new ArrayList<>();

    private Random random = new Random();

    public NewsArticlePresenter(NewsContract.View view) {
        this.view = view;
        // 混乱排版 加入额外的布局数据
        addLayout();
    }


    @Override
    public void doLoadData(String... args) {

        if(dataList.size() > 150) {
            dataList.clear();
            addLayout();
        }

        this.getRandom().subscribeOn(Schedulers.io())
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
        doLoadData();
    }

    @Override
    public void doSetAdapter(List dataBean) {
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
        Log.i(TAG, "doRefresh: ");
        if(dataList.size() >  0 ){
            dataList.clear();
            addLayout();
        }
        view.onShowLoading();
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    private Observable<NewsArticleBean>  getRandom(){
        int cursor = random.nextInt(100);
        return RetrofitFactory9001.getRetrofit().create(INewsApi.class).getRecommend(cursor);

    }


    private void  addLayout(){
        dataList.add(new BannerBean());
        dataList.add(new CategoryBean.DataBean.ListBean());
    }
}
