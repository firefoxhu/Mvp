package com.quickly.xqw;

import com.quickly.xqw.binder.circle.CircleViewBinder;
import com.quickly.xqw.binder.news.BannerViewBinder;
import com.quickly.xqw.binder.news.CategoryViewBinder;
import com.quickly.xqw.binder.LoadingEndViewBinder;
import com.quickly.xqw.binder.LoadingViewBinder;
import com.quickly.xqw.binder.news.NewsArticleImgOldViewBinder;
import com.quickly.xqw.binder.news.NewsArticleImgViewBinder;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.model.news.BannerBean;
import com.quickly.xqw.model.news.CategoryBean;
import com.quickly.xqw.model.LoadingBean;
import com.quickly.xqw.model.LoadingEndBean;
import com.quickly.xqw.model.news.NewsArticleBean;

import javax.annotation.Nonnull;

import me.drakeet.multitype.MultiTypeAdapter;

public class Register {


    public static void registerNewsArticleItem(@Nonnull MultiTypeAdapter adapter)  {
        adapter.register(BannerBean.class,new BannerViewBinder());
        adapter.register(CategoryBean.DataBean.ListBean.class,new CategoryViewBinder());

        adapter.register(NewsArticleBean.DataBean.ListBean.class)
        .to(new NewsArticleImgViewBinder(),new NewsArticleImgOldViewBinder())
        .withClassLinker((position,item)->{
            if(position  % 2 == 0) {
                return NewsArticleImgViewBinder.class;
            }else {
                return NewsArticleImgOldViewBinder.class;
            }
        });
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }


    public static void registerCircleArticle(@Nonnull MultiTypeAdapter adapter) {
        adapter.register(ArticleBean.DataBean.ListBean.class,new CircleViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }


}
