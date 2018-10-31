package com.quickly.xqw.api;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.model.circle.CommentBean;
import com.quickly.xqw.model.circle.FabulousBean;
import com.quickly.xqw.model.circle.WriteCommentBean;
import com.quickly.xqw.model.form.CommentForm;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ICircle {

    @GET(HttpUri.CIRCLE_ARTICLE)
    Observable<ArticleBean> getArticle(@Query("page") int page);

    @GET(HttpUri.CIRCLE_ARTICLE_COMMENT_LIST)
    Observable<CommentBean> getComment(@Query("page") int page,@Query("articleId") long id);

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST(HttpUri.CIRCLE_ARTICLE_COMMENT)
    Observable<WriteCommentBean> sendComment(@Body CommentForm commentForm, @Header("App-Session") String session); // 128个字符

    @GET(HttpUri.CIRCLE_ARTICLE_FABULOUS)
    Observable<FabulousBean> fabulousList(@Query("articleId") String articleId);


    @PUT(HttpUri.CIRCLE_ARTICLE_FABULOUS_ZAN)
    @FormUrlEncoded
    Observable<FabulousBean> fabulousAdd(@Field("articleId") String articleId,@Field("username")String username,@Header("App-Session") String session);

    @DELETE(HttpUri.CIRCLE_ARTICLE_FABULOUS_UN_ZAN)
    Observable<ResponseBody> fabulousCancel(@Field("fabulousId") String fabulousId,@Header("App-Session") String session);

    @POST(HttpUri.CIRCLE_ARTICLE_VIEWS)
    @FormUrlEncoded
    Observable<ResponseBody> articleViews(@Field("articleId") String articleId);



}
