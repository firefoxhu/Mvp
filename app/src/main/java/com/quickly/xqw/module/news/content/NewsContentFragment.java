package com.quickly.xqw.module.news.content;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.quickly.xqw.Constant;
import com.quickly.xqw.R;
import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.module.base.BaseFragment;
import com.quickly.xqw.utils.SettingUtil;
import com.quickly.xqw.widget.AppBarStateChangeListener;

public class NewsContentFragment extends BaseFragment<NewsContentContract.Presenter> implements NewsContentContract.View {

    private static final String TAG = "NewsContentFragment";
    private static final String IMG = "img";

    private Toolbar toolbar;
    private WebView webView;
    private NestedScrollView scrollView;

    private ContentLoadingProgressBar progressBar;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String shareUrl = "wwww.luosen365.com";

    private Bundle mBundle;
    private NewsArticleBean.DataBean.ListBean bean;

    public static NewsContentFragment newInstance(Parcelable databean){
        NewsContentFragment instance = new NewsContentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG,databean);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_news_content_img;
    }

    @Override
    protected void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        webView = view.findViewById(R.id.web_view);
        scrollView =view.findViewById(R.id.scroll_view);
        progressBar = view.findViewById(R.id.pb_progress);
        appBarLayout = view.findViewById(R.id.app_bar_layout);
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        imageView= view.findViewById(R.id.top_image);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);


        initToolBar(toolbar,true,"");
        toolbar.setOnClickListener(v->scrollView.smoothScrollBy(0,0));

        initWebClient();

        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> onHideLoading());

        int color = SettingUtil.getInstance().getColor();
        progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        progressBar.show();

        swipeRefreshLayout.setColorSchemeColors(SettingUtil.getInstance().getColor());
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
            presenter.doLoadData(bean);
        });


    }

    @Override
    protected void initData() throws NullPointerException {
        mBundle = getArguments();
        if(mBundle != null) {
            bean = mBundle.getParcelable(TAG);
        }
        presenter.doLoadData(bean);

        Glide.with(this).load(bean.getImages().get(0)).into(imageView);

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                Window window = null;
                if (getActivity() != null && getActivity().getWindow() != null) {
                    window = getActivity().getWindow();
                }
                if (state == State.EXPANDED) {
                    // 展开状态
                    collapsingToolbarLayout.setTitle(bean.getTitle());
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && window != null) {
                        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    }
                } else if (state == State.COLLAPSED) {
                    // 折叠状态

                } else {
                    // 中间状态
                    collapsingToolbarLayout.setTitle(bean.getTitle());
                    toolbar.setBackgroundColor(SettingUtil.getInstance().getColor());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && window != null) {
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    }
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        appBarLayout.setExpanded(false);

    }

    @Override
    public void onShowLoading() {
        progressBar.show();
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void onHideLoading() {

        progressBar.hide();
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void onShowNetError() {
        Snackbar.make(scrollView,"网络错误！", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(NewsContentContract.Presenter presenter) {
        if(presenter == null) {
            this.presenter = new NewsContentPresenter(this);
        }
    }

    @Override
    public void onSetWebView(String url, boolean flag) {
        // 是否为头条的网站
        if (flag) {
            webView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
        } else {
            /*
               ScrollView 嵌套 WebView, 导致部分网页无法正常加载
               如:https://temai.snssdk.com/article/feed/index/?id=11754971
               最佳做法是去掉 ScrollView, 或使用 NestedScrollWebView
             */
            if (shareUrl.contains("luosen365")) {
                webView.getSettings().setUserAgentString(Constant.USER_AGENT_PC);
            }
            webView.loadUrl(shareUrl);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void initWebClient() {
        WebSettings settings = webView.getSettings();
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
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!TextUtils.isEmpty(url)) {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                onHideLoading();
                // 注入 js 函数监听
                webView.loadUrl(Constant.JS_INJECT_IMG);
                super.onPageFinished(view, url);
            }
        });

        webView.setOnKeyListener((view, i, keyEvent) -> {
            if ((keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            return false;
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 90) {
                    onHideLoading();
                } else {
                    onShowLoading();
                }
            }
        });
        webView.addJavascriptInterface(presenter, "imageListener");
    }
}
