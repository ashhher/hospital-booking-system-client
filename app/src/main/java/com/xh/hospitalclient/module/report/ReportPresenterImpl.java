package com.xh.hospitalclient.module.report;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.xh.hospitalclient.model.ReportBean;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReportPresenterImpl extends ReportContract.ReportPresenter {
    private static final String TAG = "ReportPresenter";
    private ReportModelImpl reportModel;

    private List<ReportBean> reportList;
    private Realm realm;

    @Override
    public void loadReportList(String userId) {
        getView().showLoading();
        reportModel.getReportList(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<List<ReportBean>>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<List<ReportBean>>() {
                    @Override
                    public void onSuccess(List<ReportBean> reportBeans) {
                        reportList = reportBeans;
                        getView().hideLoading();
                        getView().showSuccess("load report list");
                        getView().bindListData(reportList);
                        getView().setAdapter();//这一步本应该是主线程执行 但目前放在这才能确保adapter数据被绑定
                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                        getView().hideLoading();
                        getView().showError("load report list");
                    }
                });
    }

    public ReportPresenterImpl(LifecycleProvider<FragmentEvent> provider) {
        super(provider);
        reportModel = ReportModelImpl.getInstance();
        reportModel.getRealm();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        reportModel.closeRealm();
    }
}
