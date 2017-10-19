package com.xie.brad.myretrofit.login.presenter;

import android.text.TextUtils;

import com.xie.brad.myretrofit.login.model.LoginModel;
import com.xie.brad.myretrofit.login.view.LoginActivity;
import com.xie.brad.myretrofit.login.view.LoginView;
import com.xie.brad.myretrofit.retrofit.BaseResultEntity;
import com.xie.brad.myretrofit.retrofit.ConnectionManager;
import com.xie.brad.myretrofit.retrofit.HttpOnNextListener;
import com.xie.brad.myretrofit.retrofit.ProgressSubscriber;
import com.xie.brad.myretrofit.retrofit.SubjectPost;

import rx.Observable;

/**
 * Created by dell on 2017/10/19.
 */

public class LoginPresenterImpl implements LoginPresenter {

    LoginView loginView;
    LoginActivity loginActivity;
    String username;
    String password;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginActivity = (LoginActivity) loginView;
    }

    @Override
    public void login(String username, String password) {
        if (!TextUtils.isEmpty(username)) {
            if (!TextUtils.isEmpty(password)) {
                this.username = username;
                this.password = password;
                getLoginData(username, password);
            } else {
                loginView.showMessage("用户名不能为空");
            }
        } else {
            loginView.showMessage("密码不能为空");
        }
    }

    private void getLoginData(String username, String password) {
        SubjectPost postEntity = new SubjectPost(new ProgressSubscriber(LoginListener, "正在登陆", loginActivity));
        ConnectionManager manager = ConnectionManager.getInstance();
        Observable<BaseResultEntity<LoginModel>> login = manager.getDataMethord().Login(username, password);
        manager.doHttpObservable(login, postEntity);

    }

    //   回调一一对应
    HttpOnNextListener LoginListener = new HttpOnNextListener<BaseResultEntity<LoginModel>>() {
        @Override
        public void onNext(BaseResultEntity<LoginModel> subjects) {
            if (subjects.getResult().equals("success")) {
                loginView.LoginSuccess(subjects.getData());
            } else {
                loginView.showMessage(subjects.getMsg());
            }
        }
    };
}
