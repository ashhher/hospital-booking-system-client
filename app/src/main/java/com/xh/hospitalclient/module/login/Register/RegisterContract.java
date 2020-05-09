package com.xh.hospitalclient.module.login.Register;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseActivityPresenter;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.User;

import rx.Observable;

public interface RegisterContract {
    abstract class RegisterModel extends BaseModel{
        public abstract Observable<User> register(String username, String password, String name, int age, boolean sex);
    }

    interface RegisterView extends BaseView {
        void toMainActivity();
    }

    abstract class RegisterPresenter extends BaseActivityPresenter<RegisterView> {
        abstract void register(String username, String password, String name, String age, String sex);
        public RegisterPresenter(LifecycleProvider<ActivityEvent> provider) {
            super(provider);
        }
    }
}
