package com.quickly.xqw.api;
import com.quickly.xqw.model.video.VideoResponseBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface VideoApi {

    @GET("nc/video/list/{type}/n/{startPage}-10.html")
    Observable<VideoResponseBean> getVideoList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type,
            @Path("startPage") int startPage);

}
