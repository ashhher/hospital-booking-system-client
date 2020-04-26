package com.xh.hospitalclient.config;

import com.xh.hospitalclient.model.entities.TestBean;
import com.xh.hospitalclient.config.Constants;
import com.xh.hospitalclient.model.entities.UserBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface APIService {

    @GET(Constants.UrlOrigin.get_info)
    Observable<TestBean> getInfoRx(@Query("id") String id);

    @GET(Constants.UrlOrigin.login)
    Observable<UserBean> loginRx(@Query("id") String username ,@Query("pwd") String password);
}