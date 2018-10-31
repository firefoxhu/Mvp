package com.quickly.xqw.module.circle.profile;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.quickly.xqw.R;
import com.quickly.xqw.R2;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.module.base2.AbstractCommentFragment;
import com.quickly.xqw.utils.ImageLoader;
import com.quickly.xqw.utils.StatusBarUtil;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import java.util.List;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalProfileUIFragment extends AbstractCommentFragment<PersonalProfileContract.Presenter> implements PersonalProfileContract.View,OnRefreshLoadMoreListener {

    private static final String TAG = "PersonalProfileUIFragment";

    private static final String TITLE = "的说说";

    private int mOffset = 0;
    private int mScrollY = 0;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.button_bar)
    ButtonBarLayout mButtonBarLayout;

    @BindView(R2.id.parallax_img)
    ImageView mBgProfileImage;

    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    @BindView(R2.id.nest_scroll)
    NestedScrollView mNestedScroll;

    @BindView(R2.id.toolbar_avatar)
    CircleImageView mToolbarImage;

    @BindView(R2.id.toolbar_title)
    ShimmerTextView mToolbarTitle;

    @BindView(R2.id.profile_nickname)
    TextView mProfileNickname;

    @BindView(R2.id.profile_avatar)
    CircleImageView mProfileAvatar;

    /**
     *  页面传值
     */
    private ArticleBean.DataBean.ListBean data;


    public static PersonalProfileUIFragment newInstance(Parcelable bean) {
        PersonalProfileUIFragment instance = new PersonalProfileUIFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG,bean);
        instance.setArguments(bundle);
        return instance;
    }


    @Override
    protected int attachLayoutId() {
        return R.layout.person_smart_list_nest_fragment;
    }

    @Override
    protected void initView(View view) {
        // this.initToolBar(mToolbar,true,"");

        mToolbar.setNavigationOnClickListener(v ->getActivity().finish());
        //状态栏透明和间距处理
        StatusBarUtil.immersive(getActivity());
        StatusBarUtil.setPaddingSmart(getActivity(), mToolbar);

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(this);
        mSmartRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                mBgProfileImage.setTranslationY(mOffset - mScrollY);
                mToolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });

        mNestedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY =20;
            private int h = DensityUtil.dp2px(170);
            private int color = ContextCompat.getColor(mContext, R.color.them)&0x00ffffff;
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    mButtonBarLayout.setAlpha(1f * mScrollY / h);
                    mToolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    mBgProfileImage.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY + 20;
            }
        });

        mButtonBarLayout.setAlpha(Color.TRANSPARENT);
       // mToolbar.setAlpha(Color.TRANSPARENT);
         mToolbar.setBackgroundColor(Color.TRANSPARENT);

    }

    @Override
    protected void initData() {

        Bundle arguments = getArguments();
        if(arguments != null) {
            data = arguments.getParcelable(TAG);
        }else {
            Toast.makeText(mContext, "参数有错误！", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            return;
        }


        ImageLoader.loadNormal(mContext,data.getAvatar(),mToolbarImage);
        mToolbarTitle.setText(data.getAuthor()+TITLE);
        ImageLoader.loadNormal(mContext,data.getAvatar(),mProfileAvatar);
        mProfileNickname.setText(data.getAuthor());
        new Shimmer().start(mToolbarTitle);


    }

    @Override
    public void onSetAdapter(List<?> list) {

    }

    @Override
    public void setPresenter(PersonalProfileContract.Presenter presenter) {
        if(presenter == null) {
            this.presenter = new PersonalProfilePresenter(this);
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        this.presenter.doLoadMoreData(refreshLayout);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        this.presenter.doRefresh(refreshLayout);
    }
}
