package com.xie.brad.myretrofit.retrofit;

import android.app.Activity;
import android.content.Intent;

import com.xie.brad.myretrofit.login.view.LoginActivity;

import rx.Subscriber;

/**
 * 测试数据
 *
 */
public class SubjectPost extends BaseEntity {
    //    回调sub
    private Subscriber mSubscriber;
    private Activity activity;

    public SubjectPost(ProgressSubscriber subscriber) {
        this.mSubscriber = subscriber;
        activity = subscriber.getActivity();
    }

    @Override
    public Subscriber getSubscirber() {
        return mSubscriber;
    }

    @Override
    public void noLogin() {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

}
