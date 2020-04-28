package com.xh.hospitalclient.module.login;

import com.xh.hospitalclient.config.APIService;
import com.xh.hospitalclient.model.UserBean;
import com.xh.hospitalclient.net.RetrofitHelper;

import io.realm.Realm;
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
    public Observable<UserBean> login(String username, String password) {
        return getAPIService().loginRx(username,password);
    }

}
