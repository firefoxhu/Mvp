package com.quickly.xqw.api;

import com.quickly.xqw.model.welfare.ZhiHuResponseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhuHuApi {


    @GET("/api/4/news/latest")
    Observable<ZhiHuResponseBean> getLastDailyList();

    @GET("/api/4/news/before/{date}")
    Observable<ZhiHuResponseBean> getDailyListWithDate(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhiHuResponseBean> getZhihuDailyDetail(@Path("id") String id);

}
