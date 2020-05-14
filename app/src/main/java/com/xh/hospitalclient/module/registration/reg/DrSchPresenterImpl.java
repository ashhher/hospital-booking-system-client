package com.xh.hospitalclient.module.registration.reg;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import io.realm.Realm;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
