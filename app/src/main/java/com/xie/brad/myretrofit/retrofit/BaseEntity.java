package com.xie.brad.myretrofit.retrofit;

import com.google.gson.Gson;

import rx.Subscriber;
import rx.functions.Func1;

/**
 * 请求数据统一封装类
 *
 */
public abstract class BaseEntity<T> implements Func1<BaseResultEntity<T>, BaseResultEntity<T>> {
/**
 * 设置参数
 * @param methods
 * @return
 */
    //  public abstract Observable getObservable(HttpService methods);

    /**
     * 设置回调sub
     *
     * @return
     */
    public abstract Subscriber getSubscirber();

    public abstract void noLogin();

    @Override
    public BaseResultEntity<T> call(BaseResultEntity<T> httpResult) {
        if (httpResult.getCode() == 100) {
          //  Hawk.put(Constant.IsLogin, false);
            noLogin();
        }
        Gson gson = new Gson();
        String toJson = gson.toJson(httpResult);
        return httpResult;
    }
}
