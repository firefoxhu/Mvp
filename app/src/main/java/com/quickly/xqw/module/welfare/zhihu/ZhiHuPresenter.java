package com.quickly.xqw.module.welfare.zhihu;

import android.support.annotation.NonNull;

import com.quickly.xqw.ErrorAction;
import com.quickly.xqw.api.GirlPhotoApi;
import com.quickly.xqw.api.HostType;
import com.quickly.xqw.api.ZhuHuApi;
import com.quickly.xqw.model.GirlDataBean;
import com.quickly.xqw.model.welfare.ZhiHuBean;
import com.quickly.xqw.model.welfare.ZhiHuResponseBean;
import com.quickly.xqw.module.welfare.girl.GirlContract;
import com.quickly.xqw.utils.RetrofitManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ZhiHuPresenter implements ZhiHuContract.Presenter {

    private final ZhiHuContract.View mView;

    private List mList = new ArrayList();


    public ZhiHuPresenter(ZhiHuContract.View view) {
        mView = view;
    }

    @Override
    public void doInitData(String... args) {

    }


    @Override
    public void doRefresh(@NonNull RefreshLayout refreshLayout) {
        this.fetch().subscribe(list->{
            if(null != list && list.size() > 0) {

                mList.clear();
                this.addLayout();

                doSetAdapter(list);
                refreshLayout.finishRefresh();
                doShowSuccess();
            }else {
                refreshLayout.finishRefresh();
            }
        },throwable ->{
            ErrorAction.print(throwable);
            doShowError();
        });
    }

    @Override
    public void doLoadMoreData(@NonNull RefreshLayout refreshLayout) {
        this.fetch().subscribe(list->{
            if(null != list && list.size() > 0) {
                doSetAdapter(list);
                refreshLayout.finishLoadMore();
            }else {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
            doShowSuccess();
        },throwable ->{
            ErrorAction.print(throwable);
            doShowError();
        });
    }

    @Override
    public void doSetAdapter(List dataBean) {
        mList.addAll(dataBean);
        mView.onSetAdapter(mList);
    }

    @Override
    public void addLayout() {

    }

    public ObservableSubscribeProxy<List<ZhiHuBean>> fetch() {
        return RetrofitManager.getDefault(HostType.HOST_ZHIHU).retrofit.create(ZhuHuApi.class).getLastDailyList().map(resp->{
            List<ZhiHuBean> list = new ArrayList<>();

            if(resp != null && resp.getTop_stories().size() > 0)
                for(int i=0;i<resp.getTop_stories().size();i++) {
                    ZhiHuResponseBean.TopStoriesBean topStoriesBean = resp.getTop_stories().get(i);
                    if(topStoriesBean.getType() == 0) {
                        list.add(new ZhiHuBean(topStoriesBean.getImage(),topStoriesBean.getId(),topStoriesBean.getTitle()));
                    }
                }

            if(resp != null && resp.getStories().size() > 0)
                for(int i=0;i<resp.getStories().size();i++) {
                    ZhiHuResponseBean.StoriesBean storiesBean = resp.getStories().get(i);
                    if(storiesBean.getType() == 0) {
                        list.add(new ZhiHuBean(storiesBean.getImages().get(0),storiesBean.getId(),storiesBean.getTitle()));
                    }
                }

            return list;

        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(mView.bindAutoDispose());
    }


    @Override
    public void doShowSuccess() {
        mView.onShowSuccess();
    }

    @Override
    public void doShowError() {
        mView.onShowNetError();
    }
}
