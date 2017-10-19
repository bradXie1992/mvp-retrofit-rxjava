package com.xie.brad.myretrofit.login.view;

import com.xie.brad.myretrofit.login.model.LoginModel;

/**
 * Created by dell on 2017/10/19.
 */

public interface LoginView {

    void showMessage(String message);

    void LoginSuccess(LoginModel loginModelImpl);
}
