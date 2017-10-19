package com.xie.brad.myretrofit.retrofit;


import com.xie.brad.myretrofit.constant.HttpService;
import com.xie.brad.myretrofit.constant.ServerUrl;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 *
 */
public class ConnectionManager {

    private HttpService httpService;
    private volatile static ConnectionManager INSTANCE;

    private ConnectionManager() {
        OkHttpClient okHttpClient = OkHttpUtils.getInstance().getOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerUrl.ServerUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        httpService = retrofit.create(HttpService.class);
    }

    /**
     * 创建 RetrofitManage 服务
     *
     * @return ApiService
     */
    public static ConnectionManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ConnectionManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionManager();
                }
            }
        }
        return INSTANCE;
    }

    public HttpService getDataMethord() {

        return INSTANCE.httpService;
    }

    /**
     * 处理http请求
     *
     * @param basePar 封装的请求数据
     */
    public void doHttpObservable(Observable dataMethord, BaseEntity basePar) {
        dataMethord.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(basePar)
                .subscribe(basePar.getSubscirber());
    }


}
