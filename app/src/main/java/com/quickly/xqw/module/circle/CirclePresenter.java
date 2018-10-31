package com.quickly.xqw.module.circle;
import android.support.annotation.NonNull;
import com.quickly.xqw.ErrorAction;
import com.quickly.xqw.api.HostType;
import com.quickly.xqw.api.ICircle;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.utils.RetrofitManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CirclePresenter implements CircleContract.Presenter {


    private final CircleContract.View mView;

    private List mList = new ArrayList();


    private int page = 0;

    public CirclePresenter(CircleContract.View view) {
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
        ++page;
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

    public ObservableSubscribeProxy<List<ArticleBean.DataBean.ListBean>> fetch() {
       return RetrofitManager
            .getDefault(HostType.HOST_8080)
            .retrofit.create(ICircle.class)
            .getArticle(page)
            .map(resp -> resp.getData().getList())
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
