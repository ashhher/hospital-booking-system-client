package com.xh.hospitalclient.module.registration.schedule.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xh.hospitalclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegTableActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.iv_dept_detail)
    ImageView ivDeptDetail;

    int id = 999;
    String title = "未绑定";
    String Info = "未绑定";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_table);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("departmentId") != null && bundle.getSerializable("departmentName") != null && bundle.getSerializable("departmentInfo") != null) {
            id = (int) bundle.getSerializable("departmentId");
            title = (String) bundle.getSerializable("departmentName");
            Info = (String) bundle.getSerializable("departmentInfo");
        }
        tvTitle.setText(title);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.iv_dept_detail)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_dept_detail:
                Intent intent = new Intent();
                intent.putExtra("departmentName",title);
                intent.putExtra("departmentInfo",Info);

                intent.setClass(this, DeptDetailActivity.class);
                startActivity(intent);
                break;
        }
    }
}