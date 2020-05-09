package com.xh.hospitalclient.module.login.Register;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.MyApplication;
import com.xh.hospitalclient.model.User;
import com.xh.hospitalclient.model.UserInfo;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterPresenterImpl extends RegisterContract.RegisterPresenter {
    private static final String TAG = "RegisterPresenter";
    private RegisterModelImpl registerModel;

    @Override
    void register(String username, String password, String name, String age, String sex) {
        getView().showLoading();

        int temp_age = Integer.parseInt(age);
        boolean temp_sex = sex.equals("男") ? true : false;
        registerModel.register(username, password, name, temp_age, temp_sex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<User>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<User>() {
                    @Override
                    public void onSuccess(User user) {
                        Log.i(TAG, "onSuccess: " + user.toString());
                        UserInfo.set(MyApplication.getInstance(), user);
                        getView().hideLoading();
                        getView().showSuccess("register");
                        getView().toMainActivity();
                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                        getView().hideLoading();
                        getView().showError("register");
                    }
                });
    }

    RegisterPresenterImpl(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        registerModel = RegisterModelImpl.getInstance();
        registerModel.getRealm();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        registerModel.closeRealm();
    }
}
