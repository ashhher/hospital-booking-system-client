package com.xh.hospitalclient.module.login.Register;

import com.xh.hospitalclient.config.APIService;
import com.xh.hospitalclient.model.UserBean;
import com.xh.hospitalclient.net.RetrofitHelper;

import rx.Observable;

public class RegisterModelImpl extends RegisterContract.RegisterModel {
    private static RegisterModelImpl registerModel;

    private RegisterModelImpl() {
        setAPIService(RetrofitHelper.getInstance().getRetrofitService());
    }
    public static RegisterModelImpl getInstance() {
        return registerModel == null ? registerModel = new RegisterModelImpl() : registerModel;
    }

    @Override
    public Observable<UserBean> register(String username, String password, String name, int age, boolean sex) {
        return getAPIService().registerRx(username,password,name,age,sex);
    }
}
