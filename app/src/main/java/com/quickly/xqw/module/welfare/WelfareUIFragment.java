package com.quickly.xqw.module.welfare;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.quickly.xqw.FragmentContainer;
import com.quickly.xqw.R;
import com.quickly.xqw.R2;
import com.quickly.xqw.adapter.TabFragmentStatePagerAdapter;
import com.quickly.xqw.module.base2.CommonFragment;
import com.quickly.xqw.module.welfare.girl.GirlTabUI;
import com.quickly.xqw.module.welfare.video.VideoUIFragment;
import com.quickly.xqw.module.welfare.zhihu.ZhiHuTabUI;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class WelfareUIFragment extends CommonFragment {

    @BindView(R2.id.tabs)
    PagerSlidingTabStrip mTabStrip;


    @BindView(R2.id.pager)
    ViewPager mViewPager;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.toolbar_title)
    TextView mToolbarTitle;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_tabs;
    }

    @Override
    protected void initView(View view) {
        this.initToolBar(mToolbar,false,"");
        mToolbar.setBackgroundColor(Color.parseColor("#76AFCF"));
        mToolbarTitle.setTextColor(Color.WHITE);
        mToolbarTitle.setText("資訊福利");

    }

    @Override
    protected void initData() {

        fragments.add(new FragmentContainer());
        fragments.add(new FragmentContainer());
        fragments.add(new FragmentContainer());
        List<String> titles = new ArrayList<>();
        titles.add("知乎");
        titles.add("图片");
        titles.add("视频");
        mViewPager.setAdapter(new TabFragmentStatePagerAdapter(getChildFragmentManager(),fragments,titles));
        mTabStrip.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Fragment fragment = fragments.get(i);
                if(fragment != null) {

                    FragmentContainer container = (FragmentContainer)fragment;

                    if(container.getFragment() == null) {
                        FragmentTransaction transaction = container.getChildFragmentManager().beginTransaction();
                        Fragment realFragment = null;
                        switch (i) {
                            case 0:
                                realFragment = new ZhiHuTabUI();
                                break;
                            case 1:
                                realFragment = new GirlTabUI();
                                break;
                            case 2:
                                realFragment = new VideoUIFragment();
                                break;
                        }
                        container.setFragment(realFragment);
                        transaction.replace(R.id.container,realFragment).show(realFragment).commit();
                    }
                }

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mViewPager.setOffscreenPageLimit(3);
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
    public void setPresenter(Object presenter) {

    }
}
