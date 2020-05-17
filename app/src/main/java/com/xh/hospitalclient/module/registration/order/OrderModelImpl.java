package com.xh.hospitalclient.module.registration.order;

import com.xh.hospitalclient.model.Registration;
import com.xh.hospitalclient.net.RetrofitHelper;

import rx.Observable;

public class OrderModelImpl extends OrderContract.OrderModel {
    private static OrderModelImpl orderModel;

    private OrderModelImpl() {
        setAPIService(RetrofitHelper.getInstance().getRetrofitService());
    }
    public static OrderModelImpl getInstance() {
        return orderModel == null ? orderModel = new OrderModelImpl() : orderModel;
    }

    @Override
    Observable<Registration> register(String userId, int schId) {
        return getAPIService().registrationRx(userId,schId);
    }
}
