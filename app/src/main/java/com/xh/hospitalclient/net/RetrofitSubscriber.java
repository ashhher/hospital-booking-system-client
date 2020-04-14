package com.xh.hospitalclient.net;

import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class RetrofitSubscriber<M> extends Subscriber<M> {
    private static final String TAG="RetrofitSubscriber";
    public abstract void onSuccess(M modelBean);
    public abstract void onError(String errorMsg);

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onCompleted() {
        Log.i(TAG, "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Log.i(TAG, "onError: "+e.getMessage());
        if(e instanceof Exception){
            if ( e instanceof JsonParseException ||e instanceof JSONException){
                onError("数据解析异常");
            }else if (e instanceof ConnectException){
                onError("网络连接异常");
            }else if (e instanceof SocketTimeoutException){
                onError("请求超时");
            }else if (e instanceof HttpException){
                onError("服务器异常");
            }else if (e instanceof UnknownHostException){
                onError("服务Host异常");
            }else {
                onError("未知异常");
            }
        }else {
            onError("未知异常");
        }
    }

    @Override
    public void onNext(M modelBean) {
        if(modelBean!=null){
                onSuccess(modelBean);
        }else {
            onError(modelBean.toString());
        }
    }

}
