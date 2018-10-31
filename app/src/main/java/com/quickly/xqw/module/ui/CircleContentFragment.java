package com.quickly.xqw.module.ui;
import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.quickly.xqw.Constant;
import com.quickly.xqw.ImageBrowserActivity;
import com.quickly.xqw.Register;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.api.ICircle;
import com.quickly.xqw.model.ErrorMessage;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.R;
import com.quickly.xqw.model.circle.CommentBean;
import com.quickly.xqw.model.form.CommentForm;
import com.quickly.xqw.module.base.BaseSmartFragment;
import com.quickly.xqw.module.contract.CircleContentContract;
import com.quickly.xqw.module.presenter.CircleContentPresenter;
import com.quickly.xqw.utils.DiffCallback;
import com.quickly.xqw.utils.RetrofitFactory8080;
import com.quickly.xqw.utils.SettingUtil;
import com.quickly.xqw.widget.CommentDialog;
import com.quickly.xqw.widget.MultiImageViewLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;
import q.rorbin.badgeview.QBadgeView;
import retrofit2.HttpException;

public class CircleContentFragment extends BaseSmartFragment<CircleContentContract.Presenter> implements CircleContentContract.View, com.scwang.smartrefresh.layout.listener.OnLoadMoreListener{

    public static final String TAG = "CircleContentFragment";

    private CircleImageView profileImage;

    private TextView nickname;

    private TextView agoDate;

    private TextView content;

    private MultiImageViewLayout multiImage;

    private TextView sendComment;

    private ImageView notifyComment;

    private ArticleBean.DataBean.ListBean bean;

    private SmartRefreshLayout mSmartRefreshLayout;

    private RecyclerView mRecyclerView;

    private MultiTypeAdapter adapter;

    private Items oldItems = new Items();


    public static CircleContentFragment newInstance(ArticleBean.DataBean.ListBean bean){
        CircleContentFragment newInstance = new CircleContentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG,bean);
        newInstance.setArguments(bundle);
        return newInstance;
    }



    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_circle_content;
    }

    @Override
    protected void initView(View view) {
        profileImage = view.findViewById(R.id.profile_image);
        nickname = view.findViewById(R.id.nickname);
        agoDate = view.findViewById(R.id.timer);
        content = view.findViewById(R.id.content);
        multiImage = view.findViewById(R.id.multi_image);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        notifyComment = view.findViewById(R.id.notify_comment);
        sendComment = view.findViewById(R.id.send_comment);
        mSmartRefreshLayout =view.findViewById(R.id.refresh_layout);

    }

    @Override
    protected void initData() throws NullPointerException {
        Bundle arguments = getArguments();
        if(arguments != null) {
            bean = arguments.getParcelable(TAG);
        }else {
            return;
        }
        this.mSmartRefreshLayout.setOnLoadMoreListener(this);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerCircleComment(adapter);
        mRecyclerView.setAdapter(adapter);

        this.mSmartRefreshLayout.autoLoadMore();


        this.presenter.doInitData(String.valueOf(bean.getArticleId()));

        Glide.with(mContext).load(bean.getAvatar()).thumbnail(0.1f).into(profileImage);
        nickname.setText(bean.getAuthor());
        agoDate.setText(bean.getCreateTime());
        content.setText(bean.getContent());

        if(bean.getPictures() != null && bean.getPictures().size() > 0) {
            multiImage.setList((ArrayList<String>)bean.getPictures());

            multiImage.setOnItemClickListener(new MultiImageViewLayout.OnItemClickListener() {
                @Override
                public void onItemClick(View view, List<String> urls, int PressImagePosition, float PressX, float PressY) {
                    ImageBrowserActivity.start(XQWApplication.appContext,urls.get(PressImagePosition), (ArrayList<String>) urls);
                }

                @Override
                public void onItemLongClick(View view, int PressImagePosition, float PressX, float PressY) {

                }
            });
        }
        new QBadgeView(mContext).bindTarget(notifyComment).setBadgeNumber(bean.getCommentsNumber());


        RxView.clicks(sendComment).throttleFirst(2, TimeUnit.SECONDS).as(bindAutoDispose()).subscribe(v->{
           final CommentDialog commentDialog = new CommentDialog(mContext,(content)->{
                RetrofitFactory8080.getRetrofit().create(ICircle.class).sendComment(new CommentForm(String.valueOf(bean.getArticleId()),content), SettingUtil.getInstance().getSession())
                        .map(x->x).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(responseBody->{

                        //TODO wrong 通知数据变化
                        CommentBean.DataBean.ListBean newComment = new CommentBean.DataBean.ListBean();
                        newComment.setContent(content);
                        newComment.setAvatar(SettingUtil.getInstance().getAvatar());
                        newComment.setNickname(SettingUtil.getInstance().getNickname());
                        List list = new ArrayList();
                        list.add(newComment);
                        // 通知 业务  业务在反向通知ui
                        this.presenter.doSetAdapter(list);

                },throwable ->{

                    ResponseBody body = ((HttpException) throwable).response().errorBody();
                    ErrorMessage errorMessage = new Gson().fromJson(body.string(),ErrorMessage.class);

                    if(errorMessage.getCode() == Constant.AUTHOR_CODE_403) {
                        Toast.makeText(mContext, "需要认证登录后才能发言！", Toast.LENGTH_SHORT).show();
                        LoginActivity.launch(TAG);
                    }

                });

            });

           if(!commentDialog.isShowing())
                commentDialog.show();
        });
    }



    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        this.presenter.doLoadMoreData(refreshLayout);
    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems  = new Items(list);
        DiffCallback.create(oldItems,newItems,adapter);
        oldItems.clear();
        oldItems.addAll(newItems);

        // 停止滑动
        mRecyclerView.stopScroll();
    }

    @Override
    public void setPresenter(CircleContentContract.Presenter presenter) {
        if(presenter == null) {
            this.presenter = new CircleContentPresenter(this);
        }
    }

    @Override
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }

}
