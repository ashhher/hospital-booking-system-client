package com.xh.hospitalclient.module.Login.Register;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.model.entities.UserBean;
import com.xh.hospitalclient.net.RetrofitSubscriber;
import com.xh.hospitalclient.widget.ToastUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterPresenterImpl extends RegisterContract.RegisterPresenter{
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
                .compose(getProvider().<UserBean>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<UserBean>() {
                    @Override
                    public void onSuccess(UserBean userBean) {
                        Log.i(TAG, "onSuccess: " + userBean.toString());
                        ToastUtil.showToast("注册成功");
                        getView().toMainActivity();
                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                        ToastUtil.showToast("注册失败");
                    }
                });
    }

    public RegisterPresenterImpl(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        registerModel = registerModel.getInstance();
    }
}
