package com.xh.hospitalclient.module.registration.department;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.base.BaseActivityPresenter;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.DeptBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

public interface DeptContract {
        abstract class DeptModel extends BaseModel {
            abstract Observable<List<DeptBean>> getDeptList();
            abstract void addDeptList(final List<DeptBean> deptList, final Realm.Transaction.OnSuccess onSuccess, final Realm.Transaction.OnError onError);
            abstract RealmResults<DeptBean> getDeptListByFather(final String father);
        }

        interface DeptView extends BaseView {
            void bindDeptListData(List<DeptBean> deptList);
            void bindFatherListData(List<String> fatherList);
            void notifyDataChanged();
            void setAdapter();
        }

        abstract class DeptPresenter extends BaseActivityPresenter<DeptView> {
            abstract void loadDeptList();
            abstract void setDeptList(String father);
            abstract List<String> getFather(List<DeptBean> deptList);
            public DeptPresenter(LifecycleProvider<ActivityEvent> provider) {
                super(provider);
            }
        }
}
