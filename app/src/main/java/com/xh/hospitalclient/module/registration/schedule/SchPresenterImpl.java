package com.xh.hospitalclient.module.registration.schedule;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
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

public class SchPresenterImpl extends SchContract.SchPresenter {
    private static final String TAG = "SchPresenterImpl";
    private SchModelImpl schModel;

    private List<Schedule> scheduleList;
    private List<Doctor> doctorList;

    public SchPresenterImpl(LifecycleProvider<FragmentEvent> provider) {
        super(provider);
        schModel = SchModelImpl.getInstance();
        schModel.getRealm();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        schModel.closeRealm();
    }

    /****************************************业务方法********************************************/
    @Override
    public void initSchList(int deptId) {
        getView().showLoading();
        schModel.loadSchListByDept(deptId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<List<Schedule>>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<List<Schedule>>() {
                    @Override
                    public void onSuccess(List<Schedule> schedules) {
                        scheduleList = schedules;
                        schModel.addSchList(schedules,new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                Log.i(TAG, "onSuccess: add schedule to realm success");
                            }
                        }, new Realm.Transaction.OnError() {
                            @Override
                            public void onError(Throwable error) {
                                Log.i(TAG, "onError: add schedule to realm fail");
                            }
                        });

                        getView().hideLoading();
                        getView().showSuccess("load schedule list");
                        getView().bindSchListData(getDate(scheduleList));
//                        getView().setAdapter();//这一步本应该是主线程执行 但目前放在这才能确保adapter数据被绑定
                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                        getView().hideLoading();
                        getView().showError("load schedule list");
                    }
                });
    }

    @Override
    public void initDrList(int deptId) {
        getView().showLoading();
        schModel.loadDrListByDept(deptId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<List<Doctor>>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<List<Doctor>>() {
                    @Override
                    public void onSuccess(List<Doctor> doctors) {
                        doctorList = doctors;
                        schModel.addDrList(doctors,new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                Log.i(TAG, "onSuccess: add doctor to realm success");
                            }
                        }, new Realm.Transaction.OnError() {
                            @Override
                            public void onError(Throwable error) {
                                Log.i(TAG, "onError: add doctor to realm fail");
                            }
                        });
                        getView().hideLoading();
                        getView().showSuccess("load doctor list");
                        getView().bindDrListData(doctorList);
                        Log.i(TAG, "onSuccess: " + doctorList.toString());
                        getView().setAdapter();//这一步本应该是主线程执行 但目前放在这才能确保adapter数据被绑定
                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                        getView().hideLoading();
                        getView().showError("load doctor list");
                    }
                });

    }

    @Override
    public List<String> getDate(List<Schedule> scheduleList) {
        List<String> dateList = new ArrayList<String>();
        for(Schedule schedule : scheduleList) {
            dateList.add(schedule.getSchDate());
        }
        HashSet set = new HashSet(dateList);
        dateList.clear();
        dateList.addAll(set);
        Collections.sort(dateList);
        return dateList;
    }
}
