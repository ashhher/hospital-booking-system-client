package com.xh.hospitalclient.module.registration.doctor;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseActivity;
import com.xh.hospitalclient.model.Department;
import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;
import com.xh.hospitalclient.utils.GlideApp;
import com.xh.hospitalclient.widget.LoadingDialog;

import java.util.List;

import butterknife.BindView;
import io.realm.Realm;

public class DoctorDetailActivity
        extends BaseActivity<DrSchContract.DrSchView, DrSchPresenterImpl>
        implements DrSchContract.DrSchView{
    private static final String TAG = "DoctorDetailActivity";

    private DrSchAdapter drSchAdapter;
    LoadingDialog loadingDialog;
    Doctor doctor;

    @BindView(R.id.tv_dr_name)
    TextView tvDrName;
    @BindView(R.id.tv_dr_pos)
    TextView tvDrPos;
    @BindView(R.id.tv_dr_dept)
    TextView tvDrDept;
    @BindView(R.id.tv_dr_info)
    TextView tvDrInfo;
    @BindView(R.id.iv_dr_avatar)
    ImageView ivDrAvatar;

    @BindView(R.id.rv_dr_sch)
    RecyclerView rvDrSch;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_doctor_detail;
    }

    @Override
    protected void initView() {
        loadingDialog = new LoadingDialog(this);
        loadingDialog.show();

        int drId = 0;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("drId") != null) {
            drId = (int) bundle.getSerializable("drId");
        }

        Realm realm = Realm.getDefaultInstance();

        List<Doctor> doctors;
        doctors = realm.where(Doctor.class)
                .equalTo("drId",drId)
                .findAll();
        doctor = doctors.get(0);

        List<Department> departments;
        departments = realm.where(Department.class)
                .equalTo("deptId",doctor.getDeptId())
                .findAll();
        Department department = departments.get(0);

        tvDrName.setText(doctor.getDrName());
        tvDrPos.setText(doctor.getDrPos());
        tvDrDept.setText(department.getDeptName());
        tvDrInfo.setText(doctor.getDrInfo());
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        GlideApp.with(getApplicationContext())
                .load("https://upload.jianshu.io/users/upload_avatars/19737964/7a5e04af-a18e-47da-8f11-ad9461d60d80?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240")
                .centerCrop()
                .apply(requestOptions)
                .into(ivDrAvatar);
        realm.close();

        rvDrSch.setLayoutManager(new LinearLayoutManager(this));
        drSchAdapter = new DrSchAdapter();
        mPresenter.initDrSchList(doctor.getDrId());

        loadingDialog.close();
    }

    @Override
    protected DrSchPresenterImpl createPresenter() {
        return new DrSchPresenterImpl(this);
    }

    @Override
    public void bindDrSchListData(List<Schedule> scheduleList) {
        drSchAdapter.setScheduleListList(scheduleList);
    }

    @Override
    public void notifyDataChanged() {
        drSchAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAdapter() {
        rvDrSch.setAdapter(drSchAdapter);
        rvDrSch.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }
}
