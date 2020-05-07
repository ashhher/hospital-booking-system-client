package com.xh.hospitalclient.module.registration.department.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseActivity;
import com.xh.hospitalclient.model.DeptBean;
import com.xh.hospitalclient.module.registration.department.DeptContract;
import com.xh.hospitalclient.module.registration.department.DeptPresenterImpl;

import java.util.List;

import butterknife.BindView;

public class DeptActivity
        extends BaseActivity<DeptContract.DeptView, DeptPresenterImpl>
        implements DeptContract.DeptView, FatherDeptAdapter.FatherDeptCallback {
    @BindView(R.id.rv_dept_father)
    RecyclerView rvDeptFather;
    @BindView(R.id.rv_dept)
    RecyclerView rvDept;

    private FatherDeptAdapter fatherDeptAdapter;
    private DeptAdapter deptAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_department;
    }

    @Override
    protected void initView() {
        rvDeptFather.setLayoutManager(new LinearLayoutManager(this));
        fatherDeptAdapter = new FatherDeptAdapter();

        rvDept.setLayoutManager(new LinearLayoutManager(this));
        deptAdapter = new DeptAdapter();

        fatherDeptAdapter.setCallback(this);

        mPresenter.loadDeptList();
    }

    @Override
    protected DeptPresenterImpl createPresenter() {
        return new DeptPresenterImpl(this);
    }

    @Override
    public void setAdapter() {
        rvDeptFather.setAdapter(fatherDeptAdapter);
        rvDept.setAdapter(deptAdapter);
    }

    @Override
    public void bindDeptListData(List<DeptBean> deptList) {
        deptAdapter.setDeptList(deptList);
    }

    @Override
    public void bindFatherListData(List<String> fatherList) {
        fatherDeptAdapter.setFatherList(fatherList);
    }

    @Override
    public void notifyDataChanged() {
        deptAdapter.notifyDataSetChanged();
    }

    @Override
    public void setDeptList(String father) {
        mPresenter.setDeptList(father);
    }
}
