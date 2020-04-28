package com.xh.hospitalclient.base;

import com.xh.hospitalclient.config.APIService;
import com.xh.hospitalclient.net.RetrofitHelper;

import io.realm.Realm;

public abstract class BaseModel {
    private APIService mApiService;
    private Realm realm;

    protected APIService getAPIService() {
        return mApiService;
    }
    protected void setAPIService(APIService apiService) {
        mApiService = apiService;
    }
    /*
     * realm相关
     * */
    public void getRealm() {
        realm = Realm.getDefaultInstance();
    }

    public void closeRealm() {
        realm.close();
    }
}
