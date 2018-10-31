package com.quickly.xqw.module.presenter;
import android.support.annotation.NonNull;
import com.quickly.xqw.api.GirlPhotoApi;
import com.quickly.xqw.api.HostType;
import com.quickly.xqw.module.contract.GirlContact;
import com.quickly.xqw.utils.RetrofitManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GirlPresenter  implements GirlContact.Presenter {


    private GirlContact.View view;

    private List<String> dataList = new ArrayList<>();

    private int page = 0;

    private int size = 20;

    public GirlPresenter(GirlContact.View view) {
        this.view = view;
    }

    @Override
    public void doInitData(String... args) {

    }

    @Override
    public void doLoadMoreData(@NonNull RefreshLayout refreshLayout) {
        this.requestData().subscribe(resp->{
            if(null != resp && resp.size() > 0) {
                ++page;
                doSetAdapter(resp);
                refreshLayout.finishLoadMore();
            }else {
                refreshLayout.finishLoadMoreWithNoMoreData();
                --page;
            }
        },throwable -> view.onShowNetError());
    }

    @Override
    public void doSetAdapter(List dataBean) {
        this.dataList.addAll(dataBean);
        this.view.onSetAdapter(this.dataList);
    }

    private ObservableSubscribeProxy<List<String>> requestData(){
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
                .as(view.bindAutoDispose());
    }

}
