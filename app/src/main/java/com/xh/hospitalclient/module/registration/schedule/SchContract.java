package com.xh.hospitalclient.module.registration.schedule;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.xh.hospitalclient.base.BaseFragmentPresenter;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;

import java.util.List;

import rx.Observable;

public interface SchContract {
    abstract class SchModel extends BaseModel {
        abstract Observable<List<Schedule>> loadSchListByDept(int deptId);
        abstract Observable<List<Doctor>> loadDrListByDept(int deptId);
    }

    interface SchView extends BaseView {
        void bindSchListData(List<Schedule> scheduleList);
        void bindDrListData(List<Doctor> doctorList);
        void notifyDataChanged();
        void setAdapter();
    }

    abstract class SchPresenter extends BaseFragmentPresenter<SchView> {
        public abstract void initSchList(int deptId);
        public abstract void initDrList(int deptId);
        public SchPresenter(LifecycleProvider<FragmentEvent> provider) {
            super(provider);
        }
    }
}
