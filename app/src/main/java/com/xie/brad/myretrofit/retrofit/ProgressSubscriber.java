package com.xie.brad.myretrofit.retrofit;

import android.app.Activity;
import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.xie.brad.myretrofit.R;
import com.xie.brad.myretrofit.utils.ActivityUtil;

import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 *
 */
public class ProgressSubscriber<T> extends Subscriber<T> {
    //    回调接口
    private HttpOnNextListener mSubscriberOnNextListener;
    //    弱引用反正内存泄露
    private WeakReference<Activity> mActivity;
    //    是否能取消请求
    private boolean cancel = true;
    //    加载框可自己定义
    private KProgressHUD dialog;

    private boolean show = true;

    public ProgressSubscriber(HttpOnNextListener mSubscriberOnNextListener, String title, Activity activity) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.mActivity = new WeakReference<>(activity);
        this.show = true;
        initProgressDialog(title);
    }

    public ProgressSubscriber(HttpOnNextListener mSubscriberOnNextListener, String title, Activity activity, boolean show) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.mActivity = new WeakReference<>(activity);
        this.show = show;
        initProgressDialog(title);
    }

    public ProgressSubscriber(HttpOnNextListener mSubscriberOnNextListener, Activity activity) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.mActivity = new WeakReference<>(activity);
    }

    public Activity getActivity() {
        if (mActivity.get() != null) {
            return mActivity.get();
        } else {
            return null;
        }
    }

    /**
     * 初始化加载框
     */
    private void initProgressDialog(String title) {
        Activity activity = mActivity.get();
        if (dialog == null && activity != null) {
            dialog = KProgressHUD.create(activity);
            dialog.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(title)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setWindowColor(activity.getResources().getColor(R.color.colorAccent))
                    .setDimAmount(0.5f);
        }
    }

    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        Context context = mActivity.get();
        if (dialog == null || context == null) return;
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * 隐藏
     */
    private void dismissProgressDialog() {

        Context context = mActivity.get();
        if (dialog != null && dialog.isShowing() && context != null) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
            onCancelProgress();
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        if (show) {
            showProgressDialog();
        }
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        if (show) {
            dismissProgressDialog();
        }
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (mActivity.get() != null) {
            if (e instanceof SocketTimeoutException) {
                ActivityUtil.toastShowError(mActivity.get(), "网络中断，请检查您的网络状态");
            } else if (e instanceof ConnectException) {
                ActivityUtil.toastShowError(mActivity.get(), "网络中断，请检查您的网络状态");
            } else if (e instanceof HttpException) {
                ActivityUtil.toastShowError(mActivity.get(), ((HttpException) e).message());
            } else if (e instanceof UnknownHostException) {
                ActivityUtil.toastShowError(mActivity.get(), "网络中断，请检查您的网络状态");
            } else if (e instanceof JSONException) {
                ActivityUtil.toastShowError(mActivity.get(), "数据错误！");
            } else {
                ActivityUtil.toastShowError(mActivity.get(), "服务器错误！");
            }
            dismissProgressDialog();
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
