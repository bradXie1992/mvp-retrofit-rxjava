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
                    
2：分包


 
