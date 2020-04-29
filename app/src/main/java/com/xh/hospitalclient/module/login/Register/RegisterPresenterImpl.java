package com.xh.hospitalclient.module.login.Register;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.model.UserBean;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterPresenterImpl extends RegisterContract.RegisterPresenter {
    private static final String TAG = "RegisterPresenter";
    private RegisterModelImpl registerModel;

    @Override
    void register(String username, String password, String name, String age, String sex) {
        getView().showLoading();//Todo: hide loading

        int temp_age = Integer.parseInt(age);
        boolean temp_sex = sex.equals("男") ? true : false;
        registerModel.register(username, password, name, temp_age, temp_sex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<UserBean>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<UserBean>() {
                    @Override
                    public void onSuccess(UserBean userBean) {
                        Log.i(TAG, "onSuccess: " + userBean.toString());
                        getView().showSuccess("register");
                        getView().toMainActivity();
                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
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
