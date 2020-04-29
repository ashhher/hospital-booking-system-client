package com.xh.hospitalclient.module.report;

import com.xh.hospitalclient.config.APIService;
import com.xh.hospitalclient.model.ReportBean;
import com.xh.hospitalclient.net.RetrofitHelper;

import java.util.List;

import io.realm.RealmResults;
import rx.Observable;

public class ReportModelImpl extends ReportContract.ReportModel {
    private static ReportModelImpl reportModel;

    private ReportModelImpl() {
        setAPIService(RetrofitHelper.getInstance().getRetrofitService());
    }
    public static ReportModelImpl getInstance() {
        return reportModel == null ? reportModel = new ReportModelImpl() : reportModel;
    }

    @Override
    Observable<List<ReportBean>> getReportList(String userId) {
        return getAPIService().getReportListRx(userId);
    }

}
