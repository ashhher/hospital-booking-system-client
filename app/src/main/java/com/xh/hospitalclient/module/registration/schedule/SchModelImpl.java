package com.xh.hospitalclient.module.registration.schedule;

import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;
import com.xh.hospitalclient.net.RetrofitHelper;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

public class SchModelImpl extends SchContract.SchModel {
    private static SchModelImpl schModel;

    private SchModelImpl() {
        setAPIService(RetrofitHelper.getInstance().getRetrofitService());
    }
    public static SchModelImpl getInstance() {
        return schModel == null ? schModel = new SchModelImpl() : schModel;
    }

    /****************************************业务方法********************************************/
    @Override
    public Observable<List<Schedule>> loadSchListByDept(int deptId) {
        return getAPIService().getScheduleListRx(deptId);
    }

    @Override
    Observable<List<Doctor>> loadDrListByDept(int deptId) {
        return getAPIService().getDoctorListRx(deptId);
    }

    @Override
    void addSchList(List<Schedule> scheduleList, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        for(final Schedule schedule : scheduleList) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(schedule);
                }
            });
        }
    }

    @Override
    void addDrList(List<Doctor> doctorList, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        for(final Doctor doctor : doctorList) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(doctor);
                }
            });
        }
    }


}
