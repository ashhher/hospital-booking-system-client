package com.xh.hospitalclient.module.registration.table;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.xh.hospitalclient.base.BaseFragmentPresenter;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseView;

import java.util.List;

import rx.Observable;

public interface RegContract {
    abstract class RegModel extends BaseModel {
//        abstract Observable<List<ReportBean>> getReportList(String userId);
    }

    interface RegView extends BaseView {
//        void bindListData(List<ReportBean> reportList);
//        void notifyDataChanged();
//        void setAdapter();
    }

    abstract class RegPresenter extends BaseFragmentPresenter<RegView> {
//        public abstract void loadReportList(String userId);
        public RegPresenter(LifecycleProvider<FragmentEvent> provider) {
            super(provider);
        }
    }
}
