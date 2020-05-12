package com.xh.hospitalclient.module.registration.schedule;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.xh.hospitalclient.base.BaseFragmentPresenter;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

public interface SchContract {
    abstract class SchModel extends BaseModel {
        public abstract Observable<List<Schedule>> loadSchListByDept(int deptId);
        abstract Observable<List<Doctor>> loadDrListByDept(int deptId);
        abstract void addSchList(List<Schedule> scheduleList, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError);
        abstract void addDrList(List<Doctor> doctorList, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError);

    }

    interface SchView extends BaseView {
        void bindSchListData(List<String> date);
        void bindDrListData(List<Doctor> doctorList);
        void notifyDataChanged();
        void setAdapter();
    }

    abstract class SchPresenter extends BaseFragmentPresenter<SchView> {
        public abstract void initSchList(int deptId);
        public abstract void initDrList(int deptId);
        public abstract List<String> getDate(List<Schedule> scheduleList);
        public SchPresenter(LifecycleProvider<FragmentEvent> provider) {
            super(provider);
        }
    }
}
