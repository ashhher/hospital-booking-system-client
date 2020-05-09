package com.xh.hospitalclient.module.report;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.xh.hospitalclient.base.BaseFragmentPresenter;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.Report;

import java.util.List;

import rx.Observable;

public interface ReportContract {
    abstract class ReportModel extends BaseModel {
        abstract Observable<List<Report>> getReportList(String userId);
    }

    interface ReportView extends BaseView {
        void bindListData(List<Report> reportList);
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
