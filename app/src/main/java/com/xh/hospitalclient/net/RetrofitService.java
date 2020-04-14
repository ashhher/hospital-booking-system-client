package com.xh.hospitalclient.net;

import com.xh.hospitalclient.Test.TestBean;
import com.xh.hospitalclient.constants.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {

    /**
     * Rx方式
     *
     * @param id   编号
     * @return Observable<TestBean>
     */
    @GET(Constants.UrlOrigin.get_info)
    Observable<TestBean> getInfoRx(@Query("id") String id);
}