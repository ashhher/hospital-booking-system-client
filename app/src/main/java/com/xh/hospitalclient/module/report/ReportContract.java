package com.xh.hospitalclient.module.report;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.xh.hospitalclient.base.BaseFragmentPresenter;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.ReportBean;

import java.util.List;

import io.realm.RealmResults;
import rx.Observable;

public interface ReportContract {
    abstract class ReportModel extends BaseModel {
        abstract Observable<List<ReportBean>> getReportList(String userId);
    }

    interface ReportView extends BaseView {
        void bindListData(List<ReportBean> reportList);
        void notifyDataChanged();
        void setAdapter();
    }

    abstract class ReportPresenter extends BaseFragmentPresenter<ReportView> {
        public abstract void loadReportList(String userId);
        public ReportPresenter(LifecycleProvider<FragmentEvent> provider) {
            super(provider);
        }
    }
}
