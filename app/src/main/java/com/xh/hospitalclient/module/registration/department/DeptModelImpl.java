package com.xh.hospitalclient.module.registration.department;

import com.xh.hospitalclient.model.DeptBean;
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
    Observable<List<DeptBean>> getDeptList() {
        return getAPIService().getDeptList();
    }

    @Override
    void addDeptList(List<DeptBean> deptList, Realm.Transaction.OnSuccess onSuccess, Realm.Transaction.OnError onError) {
        for(final DeptBean deptBean : deptList) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(deptBean);
                }
            });
        }
    }

    @Override
    RealmResults<DeptBean> getDeptListByFather(String father) {
        return realm.where(DeptBean.class)
                .equalTo("deptFather",father)
                .findAll();
    }

}
