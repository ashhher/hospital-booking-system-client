package com.xh.hospitalclient.module.login;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.MyApplication;
import com.xh.hospitalclient.model.User;
import com.xh.hospitalclient.model.UserInfo;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenterImpl extends LoginContract.LoginPresenter {
    private static final String TAG = "LoginActivityPresenter";
    private LoginModelImpl loginModel;

    @Override
    void login(String username, String password) {
        getView().showLoading();

        loginModel.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<User>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<User>() {

                    @Override
                    public void onSuccess(User user){
                        Log.i(TAG, "onSuccess: " + user.toString());
                        //放入缓存
                        UserInfo.set(MyApplication.getInstance(), user);
                        getView().hideLoading();
                        getView().showSuccess("登录");
                        getView().toMainActivity();

                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                        getView().hideLoading();
                        getView().showError("用户不存在或密码错误，登录");
                    }
                });

    }

    LoginPresenterImpl(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        loginModel = LoginModelImpl.getInstance();
        loginModel.getRealm();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        loginModel.closeRealm();
    }
}
