package com.quickly.xqw.api;

import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.model.circle.CommentBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICircle {

    @GET(HttpUri.CIRCLE_ARTICLE)
    Observable<ArticleBean> getArticle(@Query("page") int page);


    @GET(HttpUri.CIRCLE_ARTICLE_COMMENT_LIST)
    Observable<CommentBean> getComment(@Query("articleId") long id);

}
