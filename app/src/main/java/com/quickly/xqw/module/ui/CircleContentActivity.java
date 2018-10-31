package com.quickly.xqw.module.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.quickly.xqw.R;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.module.base.BaseActivity;

public class CircleContentActivity extends BaseActivity {

    private static final String TAG = "CircleContentActivity";
    private static final String IMG = "img";

    public static void launch(ArticleBean.DataBean.ListBean bean) {
        XQWApplication.appContext.startActivity(new Intent(XQWApplication.appContext, CircleContentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public static void launch(NewsArticleBean.DataBean.ListBean bean, String imgUrl) {
        XQWApplication.appContext.startActivity(new Intent(XQWApplication.appContext, CircleContentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_container);
        Intent intent = getIntent();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,
                        CircleContentFragment.newInstance(intent.getParcelableExtra(TAG)))
                .commit();
    }
}