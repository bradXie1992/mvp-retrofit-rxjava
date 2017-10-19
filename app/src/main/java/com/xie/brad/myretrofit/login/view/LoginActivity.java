package com.xie.brad.myretrofit.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xie.brad.myretrofit.R;
import com.xie.brad.myretrofit.base.BaseActivity;
import com.xie.brad.myretrofit.login.model.LoginModel;
import com.xie.brad.myretrofit.login.presenter.LoginPresenterImpl;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by dell on 2017/10/19.
 */

public class LoginActivity extends BaseActivity implements LoginView {


    @InjectView(R.id.login_text)
    TextView loginText;
    @InjectView(R.id.username_text)
    TextView usernameText;
    @InjectView(R.id.username_edittext)
    EditText usernameEdittext;
    @InjectView(R.id.pasword_text)
    TextView paswordText;
    @InjectView(R.id.pasword_edittext)
    EditText paswordEdittext;
    @InjectView(R.id.login_button)
    Button loginButton;
    private LoginPresenterImpl loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        loginPresenter = new LoginPresenterImpl(LoginActivity.this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * 访问成功的回调
     * 回调来的参数就是LoginModel
     */
    @Override
    public void LoginSuccess(LoginModel loginModels) {
        Log.e("xiebin",loginModels.getAdmin_name().toString());
    }

    @OnClick(R.id.login_button)
    public void onClick() {
        loginPresenter.login(usernameEdittext.getText().toString(), paswordEdittext.getText().toString());
    }
}
