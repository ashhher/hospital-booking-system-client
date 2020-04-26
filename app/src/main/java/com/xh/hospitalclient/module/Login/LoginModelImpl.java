package com.xh.hospitalclient.module.Login;

import com.xh.hospitalclient.config.APIService;
import com.xh.hospitalclient.model.entities.UserBean;
import com.xh.hospitalclient.net.RetrofitHelper;

import rx.Observable;

public class LoginModelImpl implements LoginContract.LoginModel {
    private static LoginModelImpl loginModel;
    private APIService mApiService;

    public static LoginModelImpl getInstance() {
        return loginModel == null ? loginModel = new LoginModelImpl() : loginModel;
    }

    private LoginModelImpl() {
        mApiService = RetrofitHelper.getInstance().getRetrofitService();
    }

    @Override
    public Observable<UserBean> login(String username, String password) {
        return mApiService.loginRx(username,password);
    }
}
