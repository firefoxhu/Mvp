package com.quickly.xqw.module.presenter;
import android.support.annotation.NonNull;
import com.quickly.xqw.api.ICircle;
import com.quickly.xqw.model.circle.CommentBean;
import com.quickly.xqw.module.contract.CircleContentContract;
import com.quickly.xqw.utils.RetrofitFactory8080;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CircleContentPresenter implements CircleContentContract.Presenter {

    private static final String TAG ="CircleContentPresenter";
    private final CircleContentContract.View view;

    private List<?> dataList = new ArrayList<>();

    private long articleId;

    private int page = 0;

    public CircleContentPresenter(CircleContentContract.View view) {
        this.view = view;
    }


    @Override
    public void doInitData(String... args) {
        this.articleId = Long.valueOf(args[0]);
        this.doViewsNumber();
    }


    @Override
    public void doLoadMoreData(@NonNull RefreshLayout refreshLayout) {
        this.requestData().subscribe(resp->{
            if(null != resp && resp.getData().getList().size() > 0) {
                ++page;
                doSetAdapter(resp.getData().getList());
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

    @Override
    public void doViewsNumber() {
        RetrofitFactory8080.getRetrofit().create(ICircle.class).articleViews(String.valueOf(articleId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(view.bindAutoDispose()).subscribe();
    }

    private ObservableSubscribeProxy<CommentBean> requestData(){
        return RetrofitFactory8080.getRetrofit().create(ICircle.class).getComment(page,articleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(view.bindAutoDispose());
    }

}
