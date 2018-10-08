package com.quickly.xqw.module.news.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.quickly.xqw.R;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.module.base.BaseActivity;

public class NewsContentActivity extends BaseActivity {

    private static final String TAG = "NewsContentActivity";
    private static final String IMG = "img";

    public static void launch(NewsArticleBean.DataBean.ListBean bean) {
        XQWApplication.appContext.startActivity(new Intent(XQWApplication.appContext, NewsContentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public static void launch(NewsArticleBean.DataBean.ListBean bean, String imgUrl) {
        XQWApplication.appContext.startActivity(new Intent(XQWApplication.appContext, NewsContentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        Intent intent = getIntent();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,
                        NewsContentFragment.newInstance(intent.getParcelableExtra(TAG)))
                .commit();
    }
}