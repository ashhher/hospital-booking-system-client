package com.xh.hospitalclient.module.registration.doctor;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.model.Schedule;

import java.util.List;

import io.realm.Realm;

public class DrSchPresenterImpl extends DrSchContract.DrSchPresenter {
    private static final String TAG = "DrSchPresenterImpl";
    private DrSchModelImpl drSchModel;

    private List<Schedule> scheduleList;

    public DrSchPresenterImpl(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        drSchModel = DrSchModelImpl.getInstance();
        drSchModel.getRealm();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        drSchModel.closeRealm();
    }

    /****************************************业务方法********************************************/

    @Override
    public void initDrSchList(int drId) {
        getView().showLoading();
        List<Schedule> scheduleList = drSchModel.loadSchListByDr(drId,new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess: load schedule list by doctor from realm");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.i(TAG, "onError:load schedule list by doctor from realm");
            }
        });
        getView().bindDrSchListData(scheduleList);
        getView().setAdapter();
        Log.i(TAG, "initDrSchList: "+ scheduleList.toString());
        getView().hideLoading();
    }
}
