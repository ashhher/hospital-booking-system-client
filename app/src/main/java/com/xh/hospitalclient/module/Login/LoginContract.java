package com.xh.hospitalclient.module.Login;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BasePresenter;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.entities.UserBean;

import rx.Observable;

public interface LoginContract {
    interface LoginModel extends BaseModel{
        Observable<UserBean> login(String username, String password);
    }

    interface  LoginView extends BaseView {
        void showLogin(String info);
        void toMainActivity();
    }

    abstract class LoginPresenter extends BasePresenter<LoginView> {
        abstract void login(String username, String password);
        public LoginPresenter(LifecycleProvider<ActivityEvent> provider) {
            super(provider);
        }
    }
}
