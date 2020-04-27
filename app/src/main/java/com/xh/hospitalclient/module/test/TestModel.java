package com.xh.hospitalclient.module.test;

import com.xh.hospitalclient.model.TestBean;
import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.net.RetrofitHelper;
import com.xh.hospitalclient.config.APIService;

import rx.Observable;

public class TestModel implements BaseModel {
    private static TestModel mtestModel;
    private APIService mApiService;

    public static TestModel getInstance() {
        return mtestModel == null ? mtestModel = new TestModel() : mtestModel;
    }

    private TestModel() {
        mApiService = RetrofitHelper.getInstance().getRetrofitService();
    }

    /**
     * @param id   编号
     * @return Observable<TestBean>
     */
    public Observable<TestBean> getInfo(String id) {
        return mApiService.getInfoRx(id);
    }
}
