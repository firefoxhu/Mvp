package com.quickly.xqw.api;
import com.quickly.xqw.model.login.LoginUserBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IUserApi {

    @FormUrlEncoded
    @POST(HttpUri.USER_LOGIN)
    Observable<LoginUserBean> login(@Field("username") String username, @Field("password") String password, @Field("session_key") String session_key);


}
