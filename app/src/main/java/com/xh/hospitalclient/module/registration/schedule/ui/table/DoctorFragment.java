package com.xh.hospitalclient.module.registration.schedule.ui.table;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xh.hospitalclient.MyApplication;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseFragment;
import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;
import com.xh.hospitalclient.module.registration.schedule.SchContract;
import com.xh.hospitalclient.module.registration.schedule.SchPresenterImpl;

import java.util.List;

import butterknife.BindView;

public class DoctorFragment
        extends BaseFragment<SchContract.SchView, SchPresenterImpl>
        implements SchContract.SchView, DoctorAdapter.DoctorCallBack{
    private static final String TAG = "DoctorFragment";

    private DoctorAdapter doctorAdapter;
    private int deptId = 999;

    @BindView(R.id.rv_doctor)
    RecyclerView rvDoctor;

    /****************************************初始化相关********************************************/
    public static DoctorFragment newInstance() {
        DoctorFragment fragment = new DoctorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_table_doctor;
    }

    @Override
    protected void initView() {
        rvDoctor.setLayoutManager(new LinearLayoutManager(getContext()));
        doctorAdapter = new DoctorAdapter();
        doctorAdapter.setCallback(this);
        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("departmentId") != null) {
            deptId = (int) bundle.getSerializable("departmentId");
        }
        mPresenter.initDrList(deptId);
    }

    @Override
    public void setAdapter() {
        rvDoctor.setAdapter(doctorAdapter);
        rvDoctor.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    @Override
    protected SchPresenterImpl createPresenter() {
        return new SchPresenterImpl(this);
    }



    /****************************************业务方法********************************************/
    @Override
    public void bindDrListData(List<Doctor> doctorList) {
        doctorAdapter.setDoctorList(doctorList);
    }

    @Override
    public void bindSchListData(List<String> date) {

    }

    /**************************************其他工具方法********************************************/
    @Override
    public void notifyDataChanged() {
        doctorAdapter.notifyDataSetChanged();
    }

}
