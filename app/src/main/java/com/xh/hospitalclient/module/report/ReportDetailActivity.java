package com.xh.hospitalclient.module.report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.xh.hospitalclient.MyApplication;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.model.ReportBean;
import com.xh.hospitalclient.model.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportDetailActivity extends AppCompatActivity {
    private static final String TAG = "ReportDetailActivity";

    @BindView(R.id.tv_report_item_title)
    TextView tvTitle;
    @BindView(R.id.tv_report_item_userName)
    TextView tvUserName;
    @BindView(R.id.tv_report_item_userAge)
    TextView tvUserAge;
    @BindView(R.id.tv_report_item_userSex)
    TextView tvUserSex;
    @BindView(R.id.tv_report_item_content)
    TextView tvContent;
    @BindView(R.id.tv_report_item_date)
    TextView tvDate;

    ReportBean report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("reportDetail") != null) {
            report = (ReportBean) bundle.getSerializable("reportDetail");
            Log.i(TAG, "initData: " + report.toString());
        }
        tvTitle.setText(report.getRptTitle());
        tvUserName.setText(UserInfo.get(MyApplication.getInstance()).getUserName());//todo:此处应是name不是id，还需要数据库获取数据]
        tvUserAge.setText(UserInfo.get(MyApplication.getInstance()).getUserAge()+"");
        String usersex = UserInfo.get(MyApplication.getInstance()).getUserSex() ? "男" : "女";
        tvUserSex.setText(usersex);
        tvContent.setText(report.getRptContent());
        tvDate.setText(report.getRptDate());
    }
}
