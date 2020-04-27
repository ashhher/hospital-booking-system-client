package com.xh.hospitalclient.module.report;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;

public class ReportPresenterImpl extends ReportContract.ReportPresenter {
    private static final String TAG = "ReportActivityPresenter";
    private ReportModelImpl reportModel;

//    @Override
//    void login(String username, String password) {
//        getView().showLoading();
//
//        loginModel.login(username, password)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(getProvider().<UserBean>bindUntilEvent(ActivityEvent.DESTROY))
//                .subscribe(new RetrofitSubscriber<UserBean>() {
//                    @Override
//                    public void onSuccess(UserBean userBean) {
//                        Log.i(TAG, "onSuccess: " + userBean.toString());
//                        getView().showError("login");
//                        getView().toMainActivity();
//                    }
//                    @Override
//                    public void onError(String errorMsg) {
//                        Log.i(TAG, "onError: fail");
//                        getView().showSuccess("login");
//                    }
//                });
//    }

    public ReportPresenterImpl(LifecycleProvider<FragmentEvent> provider) {
        super(provider);
        reportModel = ReportModelImpl.getInstance();
    }
}
