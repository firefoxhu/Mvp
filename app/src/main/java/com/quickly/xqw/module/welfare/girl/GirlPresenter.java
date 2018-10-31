package com.quickly.xqw.module.welfare.girl;
import android.support.annotation.NonNull;
import com.quickly.xqw.ErrorAction;
import com.quickly.xqw.api.GirlPhotoApi;
import com.quickly.xqw.api.HostType;
import com.quickly.xqw.utils.RetrofitManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GirlPresenter implements GirlContract.Presenter {

    private final GirlContract.View mView;

    private List mList = new ArrayList();


    private int page = 0;

    private static final int size = 10;

    public GirlPresenter(GirlContract.View view) {
        mView = view;
    }

    @Override
    public void doInitData(String... args) {

    }


    @Override
    public void doRefresh(@NonNull RefreshLayout refreshLayout) {
        this.page = 0;
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
        ++ page;
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

    public ObservableSubscribeProxy<List<String>> fetch() {
        return RetrofitManager.getDefault(HostType.HOST_GIRL).retrofit.create(GirlPhotoApi.class).getPhotoList(RetrofitManager.getCacheControl(),size,page)
                .map(resp->{
                    List<String> list = new ArrayList<>();
                    if(resp != null && resp.getResults().size()>0){
                        for (int i=0;i<resp.getResults().size();i++) {
                            list.add(resp.getResults().get(i).getUrl());
                        }
                    }
                    return list;
                })
                .subscribeOn(Schedulers.io())
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
