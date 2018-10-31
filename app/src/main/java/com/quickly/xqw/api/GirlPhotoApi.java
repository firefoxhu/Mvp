package com.quickly.xqw.api;

import com.quickly.xqw.model.GirlDataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface GirlPhotoApi {


    // https://gank.io/api/data/福利/100/1

    @GET("data/福利/{size}/{page}")
    Observable<GirlDataBean> getPhotoList(
            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);

}
