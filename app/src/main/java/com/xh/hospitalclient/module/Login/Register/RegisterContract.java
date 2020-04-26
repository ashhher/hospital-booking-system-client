package com.xh.hospitalclient.module.Login.Register;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BasePresenter;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.entities.UserBean;

import rx.Observable;

public interface RegisterContract {
    interface RegisterModel extends BaseModel{
        public Observable<UserBean> register(String username, String password, String name, int age, boolean sex);
    }

    interface RegisterView extends BaseView {
        void toMainActivity();
    }

    abstract class RegisterPresenter extends BasePresenter<RegisterView> {
        abstract void register(String username, String password, String name, String age, String sex);
        public RegisterPresenter(LifecycleProvider<ActivityEvent> provider) {
            super(provider);
        }
    }
}
