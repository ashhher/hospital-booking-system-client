package com.xh.hospitalclient.module.registration.table;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.xh.hospitalclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegTableActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.title)
    TextView tvTitle;

    String title = "未绑定";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_table);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("departmentName") != null) {
            title = (String) bundle.getSerializable("departmentName");
        }
        tvTitle.setText(title);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }
}