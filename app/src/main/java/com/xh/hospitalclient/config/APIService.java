package com.xh.hospitalclient.config;

import com.xh.hospitalclient.model.entities.TestBean;
import com.xh.hospitalclient.config.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface APIService {

    /**
     * Rx方式
     *
     * @param id   编号
     * @return Observable<TestBean>
     */
    @GET(Constants.UrlOrigin.get_info)
    Observable<TestBean> getInfoRx(@Query("id") String id);
}