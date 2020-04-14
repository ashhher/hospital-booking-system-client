package com.xh.hospitalclient.net;

import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.xh.hospitalclient.MyApplication;
import com.xh.hospitalclient.constants.Constants;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static volatile RetrofitHelper instance;
    private RetrofitService retrofitService;

    //单例
    public static RetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitHelper.class) {
                if (instance == null) {
                    instance = new RetrofitHelper();
                }
            }
        }
        return instance;
    }

    private static final int DEFAULT_TIMEOUT = 10;
    //cookie持久化
    private static ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.getInstance()));
    //设置缓存目录
    private static final File cacheDirectory = new File(MyApplication.getInstance().getCacheDir().getAbsolutePath(), "httpCache");
    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);
    //构造方法私有
    private RetrofitHelper() {
        // 创建一个OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置网络请求超时时间
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.cookieJar(cookieJar);
        // 失败后尝试重新请求
        builder.retryOnConnectionFailure(false);
        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("http", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY));
        builder.cache(cache);
        //----------------------------基本设置------------------------------------------------------
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    /**
     * @return 获取service实例
     */
    public RetrofitService getRetrofitService() {
        return retrofitService;
    }
}
