package com.quickly.xqw.module.user;
import com.quickly.xqw.R;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.quickly.xqw.R2;
import com.quickly.xqw.module.base2.CommonFragment;
import com.quickly.xqw.utils.StatusBarUtil;

import butterknife.BindView;

public class UserUIFragment extends CommonFragment<UserContract.Presenter> implements UserContract.View{


    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int attachLayoutId() {
        return R.layout.user_profile_fragment;
    }

    @Override
    protected void initView(View view) {
        initToolBar(mToolbar,false,"個人中心");
        StatusBarUtil.immersive(getActivity(),Color.parseColor("#DD6395"),0.5f);
        // mToolbar.setBackgroundColor(Color.parseColor("#DD6395"));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    public void onShowSuccess() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {

    }
}
