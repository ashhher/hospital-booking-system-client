package com.xh.hospitalclient.module.registration.reg;

import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;
import com.xh.hospitalclient.net.RetrofitHelper;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

public class DrSchModelImpl extends DrSchContract.DrSchModel {
    private static DrSchModelImpl drSchModel;

    private DrSchModelImpl() {
        setAPIService(RetrofitHelper.getInstance().getRetrofitService());
    }
    public static DrSchModelImpl getInstance() {
        return drSchModel == null ? drSchModel = new DrSchModelImpl() : drSchModel;
    }

    /****************************************业务方法********************************************/

    @Override
    List<Schedule> loadSchListByDr(int drId, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        List<Schedule> schedules;
        schedules =realm.where(Schedule.class)
                .equalTo("drId",drId)
                .findAll();
        return  schedules;
    }
//    @Override
//    void addSchList(List<Schedule> scheduleList, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
//        for(final Schedule schedule : scheduleList) {
//            realm.executeTransaction(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    realm.copyToRealmOrUpdate(schedule);
//                }
//            });
//        }
//    }



}
