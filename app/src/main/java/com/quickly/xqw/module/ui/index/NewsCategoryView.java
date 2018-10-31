package com.quickly.xqw.module.ui.index;
import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quickly.xqw.R;
import com.quickly.xqw.Register;
import com.quickly.xqw.module.base.BaseSmartRefreshListFragment;
import com.quickly.xqw.module.contract.CategoryContract;
import com.quickly.xqw.module.presenter.CategoryNewsPresenter;
import com.quickly.xqw.utils.DiffCallback;
import com.quickly.xqw.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class NewsCategoryView extends BaseSmartRefreshListFragment<CategoryContract.Presenter> implements CategoryContract.View,OnRefreshLoadMoreListener{

    private static final String TAG = "NewsCategoryView";
    private static final String IMG = "IMG";
    private static final String TITLE = "Title";

    private int mOffset = 0;
    private int mScrollY = 0;

    private String categoryId;
    private String title;
    private String url;

    private SmartRefreshLayout mSmartRefreshLayout;

    private RecyclerView mRecyclerView;

    private ImageView mImageView;

    private CircleImageView mCircleImageView;

    private TextView mTextView;

    private MultiTypeAdapter adapter;

    private Items oldItems = new Items();

    public NewsCategoryView() {
    }

    @Override
    protected int attachLayoutId() {
        //return R.layout.fragment_smart_refresh_list;
        return 0;
    }

    public static NewsCategoryView newInstance(String categoryId,String title,String url){
        NewsCategoryView instance = new NewsCategoryView();
        Bundle bundle = new Bundle();
        bundle.putString(TAG,categoryId);
        bundle.putString(IMG,url);
        bundle.putString(TITLE,title);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    protected void initView(View view) {
        final Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v-> getActivity().finish());

        //状态栏透明和间距处理
        StatusBarUtil.immersive(getActivity());
        StatusBarUtil.setPaddingSmart(getActivity(), toolbar);

        final NestedScrollView scrollView = view.findViewById(R.id.scrollView);
        final View buttonBar = view.findViewById(R.id.button_bar);


        mSmartRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mImageView = view.findViewById(R.id.parallax_img);
        mCircleImageView = view.findViewById(R.id.toolbar_avatar);
        mTextView = view.findViewById(R.id.title);

        adapter = new MultiTypeAdapter(oldItems);
        Register.registerNewsArticleItem(adapter);
        mRecyclerView.setAdapter(adapter);

        mSmartRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                mImageView.setTranslationY(mOffset - mScrollY);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(this);


        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY = 0;
            private int h = DensityUtil.dp2px(170);
            private int color = ContextCompat.getColor(getContext(), R.color.colorPrimary)&0x00ffffff;
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    buttonBar.setAlpha(1f * mScrollY / h);
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    mImageView.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY;
            }
        });
        buttonBar.setAlpha(0);
        toolbar.setBackgroundColor(0);
    }

    @Override
    protected void initData() throws NullPointerException {
        Bundle bundle = getArguments();

        if(bundle != null) {
            this.categoryId = bundle.getString(TAG);
            this.url = bundle.getString(IMG);
            this.title = bundle.getString(TITLE);
        }else{
            return;
        }
        this.presenter.doInitData(categoryId);
        Glide.with(mContext).load(url).thumbnail(0.1f).into(mImageView);
        Glide.with(mContext).load(url).thumbnail(0.1f).into(mCircleImageView);
        mTextView.setText(this.title);

        this.mSmartRefreshLayout.autoRefresh();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        this.presenter.doLoadMoreData(refreshLayout);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        this.presenter.doRefresh(refreshLayout);
    }


    @Override
    public void setPresenter(CategoryContract.Presenter presenter) {
        if(presenter == null)
            this.presenter = new CategoryNewsPresenter(this);
    }

    @Override
    public void onShowNetError() {
        // Snackbar.make(mRecyclerView,"网络异常",Snackbar.LENGTH_SHORT);
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

    /**
     * 绑定生命周期
     */
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
