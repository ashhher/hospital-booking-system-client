package com.xh.hospitalclient.config;

import com.xh.hospitalclient.model.Department;
import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Report;
import com.xh.hospitalclient.model.Schedule;
import com.xh.hospitalclient.model.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

//网络请求API
public interface APIService {

    //登录
    @GET(Constants.UrlOrigin.login)
    Observable<User> loginRx(@Query("id") String username , @Query("pwd") String password);
    //注册
    @POST(Constants.UrlOrigin.register)
    Observable<User> registerRx(@Query("id") String username , @Query("pwd") String password, @Query("name") String name, @Query("age") int age, @Query("sex") Boolean sex);
    //获取用户检查报告
    @POST(Constants.UrlOrigin.getReportList)
    Observable<List<Report>> getReportListRx(@Query("userId") String userId);
    //获取所有科室列表
    @GET(Constants.UrlOrigin.getDepartmentList)
    Observable<List<Department>> getDepartmentListRx();
    //获取科室医生
    @GET(Constants.UrlOrigin.getDoctorList)
    Observable<List<Doctor>> getDoctorListRx(@Query("deptId") int deptId);
    //获取科室排班表
    @GET(Constants.UrlOrigin.getScheduleList)
    Observable<List<Schedule>> getScheduleListRx(@Query("deptId") int deptId);

}