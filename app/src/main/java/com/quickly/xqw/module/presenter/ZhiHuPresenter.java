package com.quickly.xqw.module.presenter;

import android.support.annotation.NonNull;

import com.quickly.xqw.api.HostType;
import com.quickly.xqw.api.ZhuHuApi;
import com.quickly.xqw.model.welfare.ZhiHuResponseBean;
import com.quickly.xqw.model.welfare.ZhiHuBean;
import com.quickly.xqw.module.contract.ZhiHuContact;
import com.quickly.xqw.utils.RetrofitManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ZhiHuPresenter implements ZhiHuContact.Presenter {


    private ZhiHuContact.View view;

    private List<ZhiHuBean> dataList = new ArrayList<>();


    public ZhiHuPresenter(ZhiHuContact.View view) {
        this.view = view;
    }

    @Override
    public void doInitData(String... args) {

    }

    @Override
    public void doLoadMoreData(@NonNull RefreshLayout refreshLayout) {
        this.requestData().subscribe(resp->{
            if(null != resp && resp.size() > 0) {
                doSetAdapter(resp);
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        },throwable -> {
            throwable.printStackTrace();
            view.onShowNetError();
        });
    }

    @Override
    public void doSetAdapter(List dataBean) {
        this.dataList.addAll(dataBean);
        this.view.onSetAdapter(this.dataList);
    }

    private ObservableSubscribeProxy<List<ZhiHuBean>> requestData(){
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
        .as(view.bindAutoDispose());
    }

}
