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

    /**
     * @param loginView
     * 构造方法没传进来View，再处理完登陆逻辑后，调用View里面的方法，来对界面进行操作
     */
    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginActivity = (LoginActivity) loginView;
    }

    /**
     * @param username
     * @param password
     * 登陆的逻辑
     */
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

    /**
     * @param username
     * @param password
     * 登陆网络请求
     */
    private void getLoginData(String username, String password) {

        SubjectPost postEntity = new SubjectPost(new ProgressSubscriber(LoginListener, "正在登陆", loginActivity));
        //创建 RetrofitManage 服务
        ConnectionManager manager = ConnectionManager.getInstance();
        //执行请求
        Observable<BaseResultEntity<LoginModel>> login = manager.getDataMethord().Login(username, password);
        manager.doHttpObservable(login, postEntity);

    }

    // 请求的回掉
    HttpOnNextListener LoginListener = new HttpOnNextListener<BaseResultEntity<LoginModel>>() {
        @Override
        public void onNext(BaseResultEntity<LoginModel> loginModelBaseResultEntity) {
            if (loginModelBaseResultEntity.getResult().equals("success")) {
                loginView.LoginSuccess(loginModelBaseResultEntity.getData());
            } else {
                loginView.showMessage(loginModelBaseResultEntity.getMsg());
            }
        }
    };
}
