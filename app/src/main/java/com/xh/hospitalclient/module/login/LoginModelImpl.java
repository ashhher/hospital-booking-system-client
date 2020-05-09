package com.xh.hospitalclient.module.login;

import com.xh.hospitalclient.model.User;
import com.xh.hospitalclient.net.RetrofitHelper;

import rx.Observable;

public class LoginModelImpl extends LoginContract.LoginModel {
    private static LoginModelImpl loginModel;

    private LoginModelImpl() {
        setAPIService(RetrofitHelper.getInstance().getRetrofitService());
    }
    public static LoginModelImpl getInstance() {
        return loginModel == null ? loginModel = new LoginModelImpl() : loginModel;
    }

    @Override
    public Observable<User> login(String username, String password) {
        return getAPIService().loginRx(username,password);
    }

}
