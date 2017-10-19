package com.xie.brad.myretrofit.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Window;
import android.view.WindowManager;

import java.util.Map;


/**
 * Created by wangjian on 2016/9/21.
 */
public class ActivityUtil {

    /**
     * 041         * </br><b>title : </b>       设置Activity全屏显示
     * 042         * </br><b>description :</b>设置Activity全屏显示。
     * 043         * @param activity Activity引用
     * 044         * @param isFull true为全屏，false为非全屏
     * 045
     */
    public static void setFullScreen(Activity activity, boolean isFull) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if (isFull) {
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            window.setAttributes(params);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setAttributes(params);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * 061         * </br><b>title : </b>       隐藏系统标题栏
     * 062         * </br><b>description :</b>隐藏Activity的系统默认标题栏
     * 063         * @param activity Activity对象
     * 064
     */
    public static void hideTitleBar(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 070         * </br><b>title : </b>       设置Activity的显示方向为垂直方向
     * 071         * </br><b>description :</b>强制设置Actiity的显示方向为垂直方向。
     * 072         * @param activity Activity对象
     * 073
     */
    public static void setScreenVertical(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 079         * </br><b>title : </b>       设置Activity的显示方向为横向
     * 080         * </br><b>description :</b>强制设置Actiity的显示方向为横向。
     * 081         * @param activity Activity对象
     * 082
     */
    public static void setScreenHorizontal(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 088         * </br><b>title : </b>     隐藏软件输入法
     * 089         * </br><b>description :</b>隐藏软件输入法
     * 090         * </br><b>time :</b>       2012-7-12 下午7:20:00
     * 091         * @param activity
     * 092
     */
    public static void hideSoftInput(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * 098         * </br><b>title : </b>       使UI适配输入法
     * 099         * </br><b>description :</b>使UI适配输入法
     * 100         * </br><b>time :</b>     2012-7-17 下午10:21:26
     * 101         * @param activity
     * 102
     */
    public static void adjustSoftInput(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    /**
     * 109         * </br><b>title : </b>       跳转到某个Activity。
     * 110         * </br><b>description :</b>跳转到某个Activity
     * 111         * </br><b>time :</b>     2012-7-8 下午3:20:00
     * 112         * @param activity          本Activity
     * 113         * @param targetActivity    目标Activity的Class
     * 114
     */
    public static void switchTo(Activity activity, Class<? extends Activity> targetActivity) {

        switchTo(activity, new Intent(activity, targetActivity));

    }

    /**
     * 120         * </br><b>title : </b>       根据给定的Intent进行Activity跳转
     * 121         * </br><b>description :</b>根据给定的Intent进行Activity跳转
     * 122         * </br><b>time :</b>     2012-7-8 下午3:22:23
     * 123         * @param activity          Activity对象
     * 124         * @param intent            要传递的Intent对象
     * 125
     */
    public static void switchTo(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    /**
     * 131         * </br><b>title : </b>       带参数进行Activity跳转
     * 132         * </br><b>description :</b>带参数进行Activity跳转
     * 133         * </br><b>time :</b>     2012-7-8 下午3:24:54
     * 134         * @param activity          Activity对象
     * 135         * @param targetActivity    目标Activity的Class
     * 136         * @param params            跳转所带的参数
     * 137
     */
    public static void switchTo(Activity activity, Class<? extends Activity> targetActivity, Map<String, Object> params) {
        if (null != params) {
            Intent intent = new Intent(activity, targetActivity);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                setValueToIntent(intent, entry.getKey(), entry.getValue());
            }
            switchTo(activity, intent);
        }
    }

/*    *//**
     * 149         * </br><b>title : </b>       带参数进行Activity跳转
     * 150         * </br><b>description :</b>带参数进行Activity跳转
     * 151         * </br><b>time :</b>     2012-7-17 下午10:22:58
     * 152         * @param activity
     * 153         * @param target
     * 154         * @param params
     * 155
     *//*
    public static void switchTo(Activity activity, Class<? extends Activity> target, NameValuePair... params) {
        if (null != params) {
            Intent intent = new Intent(activity, target);
            for (NameValuePair param : params) {
                setValueToIntent(intent, param.getName(), param.getValue());
            }
            switchTo(activity, intent);
        }
    }*/


    /**
     * 168         * </br><b>title : </b>       显示Toast消息。
     * 169         * </br><b>description :</b>显示Toast消息，并保证运行在UI线程中
     * 170         * </br><b>time :</b>     2012-7-10 下午08:36:02
     * 171         * @param activity
     * 172         * @param message
     * 173
     */
    public static void toastShow(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
//                Toasty.success(activity, message).show();
            }
        });
    }

    public static void toastShowError(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
//                Toasty.error(activity, message).show();
            }
        });
    }

    /**
     * 183         * </br><b>title : </b>       将值设置到Intent里
     * 184         * </br><b>description :</b>将值设置到Intent里
     * 185         * </br><b>time :</b>     2012-7-8 下午3:31:17
     * 186         * @param intent            Inent对象
     * 187         * @param key               Key
     * 188         * @param val               Value
     * 189
     */
    public static void setValueToIntent(Intent intent, String key, Object val) {
        if (val instanceof Boolean)
            intent.putExtra(key, (Boolean) val);
        else if (val instanceof Boolean[])
            intent.putExtra(key, (Boolean[]) val);
        else if (val instanceof String)
            intent.putExtra(key, (String) val);
        else if (val instanceof String[])
            intent.putExtra(key, (String[]) val);
        else if (val instanceof Integer)
            intent.putExtra(key, (Integer) val);
        else if (val instanceof Integer[])
            intent.putExtra(key, (Integer[]) val);
        else if (val instanceof Long)
            intent.putExtra(key, (Long) val);
        else if (val instanceof Long[])
            intent.putExtra(key, (Long[]) val);
        else if (val instanceof Double)
            intent.putExtra(key, (Double) val);
        else if (val instanceof Double[])
            intent.putExtra(key, (Double[]) val);
        else if (val instanceof Float)
            intent.putExtra(key, (Float) val);
        else if (val instanceof Float[])
            intent.putExtra(key, (Float[]) val);
    }


}
