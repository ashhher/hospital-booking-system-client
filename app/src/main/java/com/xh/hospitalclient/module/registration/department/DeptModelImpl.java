package com.xh.hospitalclient.module.registration.department;

import com.xh.hospitalclient.model.Department;
import com.xh.hospitalclient.net.RetrofitHelper;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

public class DeptModelImpl extends DeptContract.DeptModel {
    private static DeptModelImpl deptModel;

    private DeptModelImpl() {
        setAPIService(RetrofitHelper.getInstance().getRetrofitService());
    }

    public static DeptModelImpl getInstance() {
        return deptModel == null ? deptModel = new DeptModelImpl() : deptModel;
    }
    @Override
    Observable<List<Department>> getDeptList() {
        return getAPIService().getDepartmentListRx();
    }

    @Override
    void addDeptList(List<Department> deptList, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        for(final Department department : deptList) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(department);
                }
            });
        }
    }

    @Override
    RealmResults<Department> getDeptListByFather(String father) {
        return realm.where(Department.class)
                .equalTo("deptFather",father)
                .findAll();
    }

}
