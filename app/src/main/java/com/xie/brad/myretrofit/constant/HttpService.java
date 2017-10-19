package com.xie.brad.myretrofit.constant;

import com.xie.brad.myretrofit.login.model.LoginModel;
import com.xie.brad.myretrofit.retrofit.BaseResultEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * service统一接口数据
 */
public interface HttpService {

    @FormUrlEncoded
    @POST(ServerUrl.LoginUrl)
    Observable<BaseResultEntity<LoginModel>> Login(@Field("admin_name") String username, @Field("admin_pass") String password);
}