package com.android.retrofitdemo.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.retrofitdemo.bean.User;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建者：tyj
 * 创建于：2018/8/28
 * 说明：
 */

public class RetrofitClient {

    /**
     * 默认超时时间
     */
    public static final int DEFAULT_TIMEOUT = 5;
    public static final int READ_TIMEOUT = 0;
    public static final int WRITE_TIMEOUT = 0;
    private OkHttpClient okHttpClient;
    private Context mContext;
    private static RetrofitClient clientInstance;
    private ApiService apiService = null;

    public static final RetrofitClient getInstance(Context context) {
        if (clientInstance == null) {
            clientInstance = new RetrofitClient(context);
        }
        return clientInstance;
    }

    private RetrofitClient(Context context) {
        this(context, null);
    }

    private RetrofitClient(Context context, String url) {
        this.mContext = context;
        if (TextUtils.isEmpty(url)) {
            url = "http://47.95.199.35:8080/jkl/";
//            url = "http://10.0.2.2:8080/jkl/";    //安卓虚拟机访问10.0.2.2替代localhost/127.0.0.1
        }
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private ObservableTransformer observableTransformer(){
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public void login(String userName, String userPwd, Observer<Result<User>> observer) {
        apiService.login(userName,userPwd).compose(observableTransformer()).subscribe(observer);
    }

    public void changePwd(Integer userId, String oldPwd, String newPwd, Observer<Result<String>> observer) {
        apiService.changePwd(userId, oldPwd, newPwd).compose(observableTransformer()).subscribe(observer);
    }

    public void resetPwd(String userInfo, String newPwd, Observer<Result> observer) {
        apiService.resetPwd(userInfo, newPwd).compose(observableTransformer()).subscribe(observer);
    }
    public void signUp(String userPhone, Observer<Result<User>> observer) {
        apiService.signUp(userPhone).compose(observableTransformer()).subscribe(observer);
    }
}
