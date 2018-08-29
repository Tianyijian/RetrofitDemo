package com.android.retrofitdemo.network;

import com.android.retrofitdemo.bean.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 创建者：tyj
 * 创建于：2018/8/28
 * 说明：
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("authority/login.action")
    Observable<Result<User>> login(@Field("userName") String userName, @Field("userPwd") String userPwd);


}
