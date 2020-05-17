package com.xh.hospitalclient.module.registration.order;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.base.BaseActivityPresenter;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.Registration;

import rx.Observable;

public interface OrderContract {
    abstract class OrderModel extends BaseModel {
        abstract Observable<Registration> register(String userId, int schId);
    }

    interface OrderView extends BaseView {
        void toRegisterSuccessActivity(int regRank);
    }

    abstract class OrderPresenter extends BaseActivityPresenter<OrderView> {
        abstract void register(String userId, int schId);
        public OrderPresenter(LifecycleProvider<ActivityEvent> provider) {
            super(provider);
        }
    }
}
