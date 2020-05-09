package com.xh.hospitalclient.module.report.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.xh.hospitalclient.MyApplication;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.model.Report;
import com.xh.hospitalclient.model.UserInfo;
import com.xh.hospitalclient.widget.LoadingDialog;

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

    Report report;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        loadingDialog = new LoadingDialog(this);
        loadingDialog.show();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("reportDetail") != null) {
            report = (Report) bundle.getSerializable("reportDetail");
            Log.i(TAG, "initData: " + report.toString());
        }
        tvTitle.setText(report.getRptTitle());
        tvUserName.setText(UserInfo.get(MyApplication.getInstance()).getUserName());
        tvUserAge.setText(UserInfo.get(MyApplication.getInstance()).getUserAge()+"");
        String usersex = UserInfo.get(MyApplication.getInstance()).getUserSex() ? "男" : "女";
        tvUserSex.setText(usersex);
        tvContent.setText(report.getRptContent());
        tvDate.setText(report.getRptDate());

        loadingDialog.close();
    }
}
