package com.quickly.xqw;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.quickly.xqw.adapter.TabFragmentStatePagerAdapter;
import com.quickly.xqw.module.base.BaseActivity;
import com.quickly.xqw.module.circle.CircleUIFragment;
import com.quickly.xqw.module.news.NewsUIFragment;
import com.quickly.xqw.module.user.UserUIFragment;
import com.quickly.xqw.module.welfare.WelfareUIFragment;
import java.util.ArrayList;
import java.util.List;
import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends BaseActivity{

    private long exitTime = 0;

    private NavigationTabBar navigationTabBar;

    private ViewPager viewPager;

    private String[] colors;

    private List<Fragment> fragments;

    private ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        navigationTabBar = findViewById(R.id.main_bottom_navigation);
        viewPager = findViewById(R.id.view_pager);
        colors = getResources().getStringArray(R.array.default_preview);

        fragments = new ArrayList<>();
        fragments.add(new FragmentContainer());
        fragments.add(new FragmentContainer());
        fragments.add(new FragmentContainer());
        fragments.add(new FragmentContainer());

        viewPager.setAdapter(new TabFragmentStatePagerAdapter(getSupportFragmentManager(), fragments,null));


        models.add(new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.ic_index), Color.parseColor(colors[0])).title("首页").build());
        models.add(new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.ic_circle), Color.parseColor(colors[1])).title("圈子").build());
        models.add(new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.ic_fuli), Color.parseColor(colors[2])).title("資訊").build());
        models.add(new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.ic_personal), Color.parseColor(colors[3])).title("个人").build());
        navigationTabBar.setModels(models);

        // 设置默认选中
        navigationTabBar.setViewPager(viewPager,0);
        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        navigationTabBar.setBehaviorEnabled(false);

        viewPager.setOffscreenPageLimit(3);

        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Fragment fragment = fragments.get(i);
                if (fragment != null) {

                    FragmentContainer container = (FragmentContainer) fragment;

                    if (container.getFragment() == null) {
                        FragmentTransaction transaction = container.getChildFragmentManager().beginTransaction();
                        Fragment realFragment = null;
                        switch (i) {
                            case 0:
                                realFragment = new NewsUIFragment();
                                break;
                            case 1:
                                realFragment = new CircleUIFragment();
                                break;
                            case 2:
                                realFragment = new WelfareUIFragment();
                                break;
                            case 3:
                                realFragment = new UserUIFragment();
                                break;
                        }
                        container.setFragment(realFragment);
                        transaction.replace(R.id.container, realFragment).show(realFragment).commit();
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

    }


    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, R.string.double_click_exit, Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }
}
