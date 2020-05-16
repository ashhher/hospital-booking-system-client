package com.xh.hospitalclient.module.registration.doctor;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.base.BaseActivityPresenter;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.Schedule;

import java.util.List;

import io.realm.Realm;

public interface DrSchContract {
    abstract class DrSchModel extends BaseModel {
        abstract List<Schedule> loadSchListByDr(int drId, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError);
    }

    interface DrSchView extends BaseView {
        void bindDrSchListData(List<Schedule> scheduleList);
        void notifyDataChanged();
        void setAdapter();
    }

    abstract class DrSchPresenter extends BaseActivityPresenter<DrSchView> {
        public abstract void initDrSchList(int drId);
        public DrSchPresenter(LifecycleProvider<ActivityEvent> provider) {
            super(provider);
        }
    }
}
