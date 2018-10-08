package com.quickly.xqw;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.quickly.xqw.module.base.BaseActivity;
import com.quickly.xqw.module.circle.CircleView;
import com.quickly.xqw.module.news.article.NewsArticleView;

public class MainActivity extends BaseActivity implements OnBottomNavigationItemClickListener {


    //底部菜单
    private BottomNavigationView mBottomNavigationView;

    private long exitTime = 0;

    private static final String POSITION ="position";
    private int position = 0;

    private static final int MENU_HOME = 0;
    private static final int MENU_CIRCLE = 1;
    private static final int MENU_VIDEO = 2;
    private Fragment[] fragments = new Fragment[3];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.main_navigation);

        BottomNavigationItem navHome = new BottomNavigationItem("首页", ContextCompat.getColor(this, R.color.colorAccent), R.drawable.ic_launcher_background);
        BottomNavigationItem navCircle = new BottomNavigationItem("圈子", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_launcher_background);
        BottomNavigationItem navVideo = new BottomNavigationItem("视频", ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_launcher_background);
        mBottomNavigationView.addTab(navHome);
        mBottomNavigationView.addTab(navCircle);
        mBottomNavigationView.addTab(navVideo);
        mBottomNavigationView.setOnBottomNavigationItemClickListener(this);

        //初始化首页fragment
        fragments[MENU_HOME] = NewsArticleView.newInstance();

        getSupportFragmentManager().beginTransaction().add(R.id.main_container, fragments[MENU_HOME]).commit();

        if(savedInstanceState != null) {
            mBottomNavigationView.selectTab(savedInstanceState.getInt(POSITION));
        }else {
            mBottomNavigationView.selectTab(MENU_HOME);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // recreate 时记录当前位置 (在 Manifest 已禁止 Activity 旋转,所以旋转屏幕并不会执行以下代码)
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, position);
    }

    @Override
    public void onNavigationItemClick(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        boolean isNull = fragments[index] == null ? true : false;
        switch (index) {
            case MENU_HOME:
                if(isNull) {
                    fragments[index] = NewsArticleView.newInstance();
                    ft.add(R.id.main_container,fragments[index]);
                }
                break;
            case MENU_CIRCLE:
                if(isNull) {
                    fragments[index] = CircleView.newInstance();
                    ft.add(R.id.main_container,fragments[index]);
                }
                break;
            case MENU_VIDEO:
                if(isNull) {
                    fragments[index] = NewsArticleView.newInstance();
                    ft.add(R.id.main_container,fragments[index]);
                }
                break;
        }
        ft.show(fragments[index]).commit();

    }


    private void hideFragment(FragmentTransaction ft) {
        for(Fragment fragment: fragments) {
            if(fragment != null)
                ft.hide(fragment);
        }
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
