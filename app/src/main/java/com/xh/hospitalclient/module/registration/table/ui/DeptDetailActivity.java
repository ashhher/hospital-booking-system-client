package com.xh.hospitalclient.module.registration.table.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xh.hospitalclient.R;

import javax.xml.transform.Templates;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeptDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_dept_name)
    TextView tvDeptName;
    @BindView(R.id.tv_dept_info)
    TextView tvDeptInfo;

    String title = "未绑定";
    String Info = "未绑定";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("departmentName") != null && bundle.getSerializable("departmentInfo") != null) {
            title = (String) bundle.getSerializable("departmentName");
            Info = (String) bundle.getSerializable("departmentInfo");
        }
        tvDeptName.setText(title);
        tvDeptInfo.setText(Info);
    }
}
