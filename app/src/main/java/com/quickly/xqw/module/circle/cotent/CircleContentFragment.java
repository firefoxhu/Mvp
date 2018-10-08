package com.quickly.xqw.module.circle.cotent;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.quickly.xqw.R;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.module.base.BaseFragment;
import com.quickly.xqw.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

public class CircleContentFragment extends BaseFragment<CircleContentContract.Presenter> implements CircleContentContract.View {

    private static final String TAG =  "CircleContentFragment";
    private Bundle mBundle;
    private ArticleBean.DataBean.ListBean bean;

    private int mOffset = 0;
    private int mScrollY = 0;



    public static CircleContentFragment  newInstance(Parcelable dataBean) {
        CircleContentFragment instance = new CircleContentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG,dataBean);
        instance.setArguments(bundle);
        return instance;
    }



    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_circle_content;
    }

    @Override
    protected void initView(View view) {
        final Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        //状态栏透明和间距处理
        StatusBarUtil.immersive(getActivity());
        StatusBarUtil.setPaddingSmart(getActivity(), toolbar);

        final View parallax = view.findViewById(R.id.parallax);
        final View buttonBar = view.findViewById(R.id.buttonBarLayout);
        final NestedScrollView scrollView = view.findViewById(R.id.scrollView);
        final RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);

        view.findViewById(R.id.attention).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"点击了关注",Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.leaveword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"点击了留言",Toast.LENGTH_SHORT).show();
            }
        });

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(3000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                parallax.setTranslationY(mOffset - mScrollY);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });

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
                    parallax.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY;
            }
        });
        buttonBar.setAlpha(0);
        toolbar.setBackgroundColor(0);
    }

    @Override
    protected void initData() throws NullPointerException {

    }

    @Override
    public void onSetBean(ArticleBean.DataBean.ListBean bean) {

    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(CircleContentContract.Presenter presenter) {

        if(presenter == null) {
            this.presenter  = new CircleContentPresenter(this);
        }

    }
}
