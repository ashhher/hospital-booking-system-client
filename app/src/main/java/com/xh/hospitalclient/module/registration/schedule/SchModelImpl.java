package com.xh.hospitalclient.module.registration.schedule;

import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;
import com.xh.hospitalclient.net.RetrofitHelper;

import java.util.List;

import rx.Observable;

public class SchModelImpl extends SchContract.SchModel {
    private static SchModelImpl schModel;

    private SchModelImpl() {
        setAPIService(RetrofitHelper.getInstance().getRetrofitService());
    }
    public static SchModelImpl getInstance() {
        return schModel == null ? schModel = new SchModelImpl() : schModel;
    }

    /****************************************业务方法********************************************/
    @Override
    Observable<List<Schedule>> loadSchListByDept(int deptId) {
        return getAPIService().getScheduleListRx(deptId);
    }

    @Override
    Observable<List<Doctor>> loadDrListByDept(int deptId) {
        return getAPIService().getDoctorListRx(deptId);
    }
}
