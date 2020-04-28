package com.xh.hospitalclient.module.report;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.xh.hospitalclient.base.BaseFragmentPresenter;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.base.BaseView;

public interface ReportContract {
    abstract class ReportModel extends BaseModel {
//        Observable<ReportBean> login(String username, String password);
    }

    interface ReportView extends BaseView {
//        void toMainActivity();
    }

    abstract class ReportPresenter extends BaseFragmentPresenter<ReportView> {
//        abstract void login(String username, String password);
        public ReportPresenter(LifecycleProvider<FragmentEvent> provider) {
            super(provider);
        }
    }
}
