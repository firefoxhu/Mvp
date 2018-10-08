package com.quickly.xqw.api;
import com.quickly.xqw.model.news.CategoryBean;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ICategoryApi {

    /**
     * 类别
     * classId ： 新闻资讯 2904e1433c9541939e36f8b45e1abbdc
     * classId ：
     */
    @GET("category?classId=2904e1433c9541939e36f8b45e1abbdc&size=16")
    Observable<CategoryBean> getNewsCategory();





}
