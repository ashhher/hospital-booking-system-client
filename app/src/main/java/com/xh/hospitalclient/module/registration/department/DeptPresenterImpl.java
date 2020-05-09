package com.xh.hospitalclient.module.registration.department;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.model.Department;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.realm.Realm;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeptPresenterImpl extends DeptContract.DeptPresenter {
    private static final String TAG = "DeptPresenterImpl";

    private DeptModelImpl deptModel;
    private List<Department> deptList;

    public DeptPresenterImpl(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        deptModel = DeptModelImpl.getInstance();
        deptModel.getRealm();
    }

    @Override
    public void loadDeptList() {
        deptModel.getDeptList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<List<Department>>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<List<Department>>() {
                    @Override
                    public void onSuccess(List<Department> departments) {
                        deptList = departments;
                        deptModel.addDeptList(departments,new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                Log.i(TAG, "onSuccess: add department to realm success");
                            }
                        }, new Realm.Transaction.OnError() {
                            @Override
                            public void onError(Throwable error) {
                                Log.i(TAG, "onError: add department to realm fail");
                            }
                        });

                        getView().showSuccess("load department list");
                        List<String> father = getFather(deptList);
                        getView().bindFatherListData(father);
                        getView().bindDeptListData(deptModel.getDeptListByFather(father.get(0)));
                        getView().setAdapter();//这一步本应该是主线程执行 但目前放在这才能确保adapter数据被绑定
                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                        getView().showError("load department list");
                    }
                });
    }

    @Override
    public void setDeptList(String father) {
        getView().bindDeptListData( deptModel.getDeptListByFather(father));
        getView().notifyDataChanged();
    }

    @Override
    public List<String> getFather(List<Department> deptList) {
        List<String> fatherDeptList = new ArrayList<String>();
        for(Department department : deptList) {
            fatherDeptList.add(department.getDeptFather());
        }
        HashSet set = new HashSet(fatherDeptList);
        fatherDeptList.clear();
        fatherDeptList.addAll(set);
        return fatherDeptList;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        deptModel.closeRealm();
    }
}
