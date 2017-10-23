# mvp-retrofit-rxjava

 android 网络请求框架，MVP+Retrofit+Rxjava
 
 1：这里对请求的数据也进行了封装， 服务端json格式要求：
 
 
         {
              "Code": 800,
              "data": {},
              "line": 108,
              "msg": "成功",
               "result": "success"
           }
           
# 如何运用此框架
 
1：配置你的 build.gradle 的 dependencies 加入项目所需要的依赖。
 
             dependencies {
                    compile fileTree(dir: 'libs', include: ['*.jar'])
                    compile 'com.android.support:appcompat-v7:23.0.0'
                    testCompile 'junit:junit:4.12'
                    compile 'com.zhy:okhttputils:2.6.2'
                    compile 'io.reactivex:rxandroid:1.1.0'
                    /*rx-android-java*/
                    compile 'io.reactivex:rxjava:+'
                    compile 'com.trello:rxlifecycle:+'
                    compile 'com.trello:rxlifecycle-components:+'
                    /*rotrofit*/
                    compile 'com.squareup.retrofit2:retrofit:+'
                    compile 'com.squareup.retrofit2:converter-gson:+'
                    compile 'com.squareup.retrofit2:adapter-rxjava:+'
                    compile 'com.google.code.gson:gson:+'
                    /*注入工具*/
                    compile 'com.jakewharton:butterknife:5.1.1'
                    /*加载框*/
                    compile 'com.kaopiz:kprogresshud:1.0.2'
                    }
                    
2：分包,这里可以清楚的看到项目的分包结构。

![Aaron Swartz](https://github.com/bradXie1992/mvp-retrofit-rxjava/blob/master/mvp_picture.png?raw=true)


3：如何快速运用此框架，后面会对请求进行详细解释

  第一步：constant 包，这个包里面主要存放的是常量。
  
  ServerUrl：
  
  
                       package com.xie.brad.myretrofit.constant;
                       /**
                        * 这里是请求的链接
                        */
                       public class ServerUrl {
                           //请求共用的头部
                            public static final String ServerUrl = "http://......";
                           //登录接口
                           public static final String LoginUrl = ".php?interface=login";

                       } 
                       
 
 
  HttpService:这个接口里面是retrofit的请求方式，对retrofit不太懂的可以先去了解一下
  
  
      package com.xie.brad.myretrofit.constant;
      import com.xie.brad.myretrofit.login.model.LoginModel;
      import com.xie.brad.myretrofit.retrofit.BaseResultEntity;
      import retrofit2.http.Field;
      import retrofit2.http.FormUrlEncoded;
      import retrofit2.http.POST;
      import rx.Observable;
      /**
       * service统一接口数据,retrofit的请求方式
       */
      public interface HttpService {

          @FormUrlEncoded
          @POST(ServerUrl.LoginUrl)
          Observable<BaseResultEntity<LoginModel>> Login(@Field("admin_name") String username, @Field("admin_pass") String password);
      }
      
      这里说下BaseResultEntity这个类，这个类在retrofit包里面，主要是对请求过来的json进行统一处理，上面说过，这个框架需要服务器传输的json
      的数据形式。这样做的好处是，我可以吧一些异常信息统一在请求类里面捕获，防止在进行json转化的时候，造成程序崩溃。
      
      

  第二步：功能包，也就是login包
  
     model：这个就不用说了，这里面存放的是bean类。
     
     presenter:这个包里面是请求的业务逻辑。
     
     LoginPresenter：这个接口里面写的是主要的功能，这里我要做的是登陆功能，所以要写一个登陆方法，参数是用户名和密码。
     
     
          void login(String username , String pasword);
          
          
      LoginPresenterImpl：这个类要实现 LoginPresenter这个接口，实现接口的登陆方法。
      
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
          
          
          

 第三步：View包
 
  LoginView：这个接口里面写的是对界面操作的方法。
  
  
         void showMessage(String message);
         void LoginSuccess(LoginModel loginModelImpl);
            
         
  
   LoginActivity:这就是我们所看到的Activity界面
   
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
       
       
       
  
  
 
