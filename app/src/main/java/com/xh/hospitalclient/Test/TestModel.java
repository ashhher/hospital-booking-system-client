package com.xh.hospitalclient.Test;

import com.xh.hospitalclient.base.BaseModel;
import com.xh.hospitalclient.net.RetrofitHelper;
import com.xh.hospitalclient.net.RetrofitService;

import rx.Observable;

public class TestModel implements BaseModel {
    private static TestModel testModel;
    private RetrofitService retrofitService;

    public static TestModel getInstance() {
        return testModel == null ? testModel = new TestModel() : testModel;
    }

    private TestModel() {
        retrofitService = RetrofitHelper.getInstance().getRetrofitService();
    }

    /**
     * @param id   编号
     * @return Observable<TestBean>
     */
    public Observable<TestBean> getInfo(String id) {
        return retrofitService.getInfoRx(id);
    }
}
