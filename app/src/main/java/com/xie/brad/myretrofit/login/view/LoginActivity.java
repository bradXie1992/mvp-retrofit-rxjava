package com.xie.brad.myretrofit.login.view;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
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

    @InjectView(R.id.username_edittext)
    EditText usernameEdittext;
    @InjectView(R.id.pasword_edittext)
    EditText paswordEdittext;
    private LoginPresenterImpl loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        init();
    }

    /**
     * 这里给LoginPresenterImpl的构造方法传入参数
     */
    private void init() {
        loginPresenter = new LoginPresenterImpl(LoginActivity.this);
    }

    /**
     * @param message
     * 这个方法再 LoginPresenterImpl 这个类中会调用
     */
    @Override
    public void showMessage(String message) {
        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * 访问成功的回调  再 LoginPresenterImpl 类中调用
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
