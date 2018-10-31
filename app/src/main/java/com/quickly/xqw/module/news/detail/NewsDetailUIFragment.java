package com.quickly.xqw.module.news.detail;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.quickly.xqw.Constant;
import com.quickly.xqw.R;
import com.quickly.xqw.R2;
import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.module.base2.CommonFragment;
import com.quickly.xqw.utils.SettingUtil;
import com.quickly.xqw.widget.NestedScrollWebView;
import butterknife.BindView;

public class NewsDetailUIFragment extends CommonFragment<NewsDetailContract.Presenter> implements NewsDetailContract.View {

    private static final String TAG ="NewsDetailUIFragment";
    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.app_bar_layout)
    AppBarLayout mAppBarLayout;

    @BindView(R2.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R2.id.iv_detail)
    ImageView mImageView;

    @BindView(R2.id.tv_detail_title)
    TextView  mTitle;

    @BindView(R2.id.tv_detail_copyright)
    TextView  mSource;

    
    @BindView(R2.id.nswv_detail_content)
    NestedScrollWebView mNestedScrollWebView;

    private NewsArticleBean.DataBean.ListBean data;


    public static NewsDetailUIFragment newInstance(Parcelable bean) {
        NewsDetailUIFragment newInstance = new NewsDetailUIFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG,bean);
        newInstance.setArguments(bundle);
        return newInstance;
    }


    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected void initView(View view) {
       //this.initToolBar(mToolbar,true,"详情");
       mToolbar.setNavigationOnClickListener(v -> getActivity().finish());
        this.initWebClient();
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            this.data = bundle.getParcelable(TAG);
        }

        Glide.with(this).load(data.getImages().get(0)).into(mImageView);
        mTitle.setText(data.getTitle());
        mSource.setText(data.getResource());


        this.presenter.doInitData(data.getContent());

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
    public void setPresenter(NewsDetailContract.Presenter presenter) {
        if(presenter == null) {
            this.presenter = new NewsDetailPresenter(this);
        }
    }

    @Override
    public void onSetWebView(String url) {
        mNestedScrollWebView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
    }

    @Override
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    public void initWebClient() {
        WebSettings settings = mNestedScrollWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        // 缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        settings.setBuiltInZoomControls(false);
        // 缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启DOM storage API功能
        settings.setDomStorageEnabled(true);
        // 开启application Cache功能
        settings.setAppCacheEnabled(true);
        // 判断是否为无图模式
        settings.setBlockNetworkImage(SettingUtil.getInstance().getIsNoPhotoMode());
        // 不调用第三方浏览器即可进行页面反应
        mNestedScrollWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!TextUtils.isEmpty(url)) {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mNestedScrollWebView.loadUrl(Constant.JS_INJECT_IMG);
                super.onPageFinished(view, url);
            }
        });

        mNestedScrollWebView.setOnKeyListener((view, i, keyEvent) -> {
            if ((keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) && mNestedScrollWebView.canGoBack()) {
                mNestedScrollWebView.goBack();
                return true;
            }
            return false;
        });

        mNestedScrollWebView.addJavascriptInterface(this.presenter, "imageListener");
    }
}
