package com.xh.hospitalclient.module.login;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.model.UserBean;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenterImpl extends LoginContract.LoginPresenter {
    private static final String TAG = "LoginActivityPresenter";
    private LoginModelImpl loginModel;

    @Override
    void login(String username, String password) {
        getView().showLoading();//Todo: hide loading

        loginModel.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<UserBean>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<UserBean>() {
                    @Override
                    public void onSuccess(UserBean userBean) {
                        Log.i(TAG, "onSuccess: " + userBean.toString());
                        getView().showSuccess("login");
                        getView().toMainActivity();
                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                        getView().showError("login");
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
