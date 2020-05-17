package com.xh.hospitalclient.module.registration.order;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.xh.hospitalclient.model.Registration;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderPresenterImpl extends OrderContract.OrderPresenter{
    private static final String TAG = "OrderPresenter";
    private OrderModelImpl orderModel;

    @Override
    void register(String userId, final int schId) {
        getView().showLoading();

        orderModel.register(userId, schId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<Registration>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RetrofitSubscriber<Registration>() {
                    @Override
                    public void onSuccess(Registration registration){
                        Log.i(TAG, "onSuccess: " + registration.toString());
                        getView().hideLoading();
                        getView().toRegisterSuccessActivity(registration.getRegRank());
                    }
                    @Override
                    public void onError(String errorMsg) {
                        Log.i(TAG, "onError: fail");
                        getView().hideLoading();
                    }
                });
    }

    OrderPresenterImpl(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        orderModel = OrderModelImpl.getInstance();
        orderModel.getRealm();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        orderModel.closeRealm();
    }


}
