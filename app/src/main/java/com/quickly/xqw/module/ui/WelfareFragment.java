package com.quickly.xqw.module.ui;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.astuetz.PagerSlidingTabStrip;
import com.quickly.xqw.R;
import com.quickly.xqw.adapter.TabFragmentStatePagerAdapter;
import com.quickly.xqw.module.base.BaseSmartFragment;

import java.util.ArrayList;
import java.util.List;

public class WelfareFragment extends BaseSmartFragment {

    private PagerSlidingTabStrip tabs;

    private ViewPager pager;

    private List<Fragment> fragments = new ArrayList<>();



    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_tabs;
    }

    @Override
    protected void initView(View view) {

        this.initToolBar(view.findViewById(R.id.toolbar),false,"娱乐资讯");

        this.tabs = view.findViewById(R.id.tabs);
        this.pager = view.findViewById(R.id.pager);
    }

    @Override
    protected void initData() throws NullPointerException {



        fragments.add(new ZhiHuTab());
        fragments.add(new GirlTab());


        pager.setAdapter(new TabFragmentStatePagerAdapter(getChildFragmentManager(),fragments,null));
        tabs.setViewPager(pager);
    }

    @Override
    protected void setPresenter(Object presenter) {

    }
}
