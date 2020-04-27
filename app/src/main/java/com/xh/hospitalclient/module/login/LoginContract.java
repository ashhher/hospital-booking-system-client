package com.xh.hospitalclient.module.login;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseActivityPresenter;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.UserBean;

import rx.Observable;

public interface LoginContract {
    interface LoginModel extends BaseModel{
        Observable<UserBean> login(String username, String password);
    }

    interface LoginView extends BaseView {
        void toMainActivity();
    }

    abstract class LoginActivityPresenter extends BaseActivityPresenter<LoginView> {
        abstract void login(String username, String password);
        public LoginActivityPresenter(LifecycleProvider<ActivityEvent> provider) {
            super(provider);
        }
    }
}
