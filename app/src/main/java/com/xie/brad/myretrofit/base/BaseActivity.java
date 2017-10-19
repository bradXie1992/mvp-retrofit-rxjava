package com.xie.brad.myretrofit.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xie.brad.myretrofit.R;
import com.xie.brad.myretrofit.widget.StatusBarUtils;


/**
 * Created by wangjian on 2016/9/19.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.colorPrimaryDark);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
