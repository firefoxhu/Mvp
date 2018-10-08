package com.quickly.xqw.api;

import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.model.news.NewsContent;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface INewsApi {

    String HOST = "";

    @GET("news/recommend?size=8")
    Observable<NewsArticleBean> getRecommend(@Query("page") int page);

    @GET("news/detail")
    Observable<NewsContent> getNewsDetail(@Query("id") String id);


}
