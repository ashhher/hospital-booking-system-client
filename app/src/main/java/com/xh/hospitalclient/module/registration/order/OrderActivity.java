package com.xh.hospitalclient.module.registration.order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xh.hospitalclient.MyApplication;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseActivity;
import com.xh.hospitalclient.model.Department;
import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;
import com.xh.hospitalclient.model.User;
import com.xh.hospitalclient.model.UserInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;

public class OrderActivity
        extends BaseActivity<OrderContract.OrderView, OrderPresenterImpl>
        implements OrderContract.OrderView {

    Schedule schedule;
    User user;

    @BindView(R.id.tv_reg_date)
    TextView tvDate;
    @BindView(R.id.tv_reg_doctor)
    TextView tvDoctor;
    @BindView(R.id.tv_reg_dept)
    TextView tvDept;
    @BindView(R.id.tv_reg_patient)
    TextView tvPatient;
    @BindView(R.id.tv_reg_psex)
    TextView tvSex;
    @BindView(R.id.tv_reg_page)
    TextView tvAge;
    @BindView(R.id.tv_reg_ptel)
    TextView tvTel;

    @BindView(R.id.btn_registration)
    Button btnRegistration;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        int schId = 0;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("schId") != null) {
            schId = (int) bundle.getSerializable("schId");
        }

        Realm realm = Realm.getDefaultInstance();
        schedule = realm.where(Schedule.class)
                .equalTo("schId",schId)
                .findFirst();
        Doctor doctor =  realm.where(Doctor.class)
                .equalTo("drId",schedule.getDrId())
                .findFirst();
        Department department = realm.where(Department.class)
                .equalTo("deptId", schedule.getDeptId())
                .findFirst();

        user = UserInfo.get(MyApplication.getInstance());
        String sex= user.getUserSex()? "男":"女";

        tvDate.setText(schedule.getSchDate());
        tvDoctor.setText(doctor.getDrName());
        tvDept.setText(department.getDeptName());
        tvPatient.setText(user.getUserName());
        tvSex.setText(sex);
        tvAge.setText(user.getUserAge()+"");
        tvTel.setText(user.getUserId());

    }


    @Override
    protected OrderPresenterImpl createPresenter() {
        return new OrderPresenterImpl(this);
    }

    @Override
    public void toRegisterSuccessActivity(int regRank) {
        Intent intent = new Intent();
        intent.putExtra("rank",regRank);
        intent.setClass(this, RegisterSuccessActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.btn_registration})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_registration:
                mPresenter.register(user.getUserId(),schedule.getSchId());
                break;
        }
    }
}
