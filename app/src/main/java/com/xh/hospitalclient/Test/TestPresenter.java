package com.xh.hospitalclient.Test;


import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.base.BasePresenter;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TestPresenter extends BasePresenter<TestView> {
    private static final String TAG = "TestPresenter";
    private TestModel testModel;

    public TestPresenter(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        testModel = TestModel.getInstance();
    }

    public void getInfo(String id) {
        getView().showLoading();

        testModel.getInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<TestBean>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<TestBean>() {
                    @Override
                    public void onSuccess(TestBean testBean) {
                        Log.i(TAG, "onSuccess: " + testBean.toString());
                        getView().updateInfo(testBean);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                    }
                });
    }
}
