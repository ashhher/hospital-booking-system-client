package com.xh.hospitalclient.module.Login;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.model.entities.UserBean;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenterImpl extends LoginContract.LoginPresenter{
    private static final String TAG = "LoginPresenter";
    private LoginModelImpl loginModel;

    @Override
    void login(String username, String password) {
        getView().showLoading();

        loginModel.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<UserBean>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<UserBean>() {
                    @Override
                    public void onSuccess(UserBean userBean) {
                        Log.i(TAG, "onSuccess: " + userBean.toString());
                        getView().showLogin("登录成功");
                        getView().toMainActivity();
                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                        getView().showLogin("登录失败");
                    }
                });
    }

    public LoginPresenterImpl(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        loginModel = LoginModelImpl.getInstance();
    }
}
