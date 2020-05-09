package com.xh.hospitalclient.module.report.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xh.hospitalclient.MyApplication;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseFragment;
import com.xh.hospitalclient.model.Report;
import com.xh.hospitalclient.model.UserInfo;
import com.xh.hospitalclient.module.report.ReportContract;
import com.xh.hospitalclient.module.report.ReportPresenterImpl;

import java.util.List;

import butterknife.BindView;

public class ReportFragment
        extends BaseFragment<ReportContract.ReportView, ReportPresenterImpl>
        implements ReportContract.ReportView, ReportAdapter.ReportCallBack{
    private static final String TAG = "ReportFragment";

    @BindView(R.id.rv_report)
    RecyclerView rvReport;

    private ReportAdapter reportAdapter;

    /****************************************初始化相关********************************************/
    //绑定界面
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_report;
    }

    //new adapter -> presenter内进行网络请求并set数据到adapter中 -> recyclerView.setAdapter
    @Override
    protected void initView() {
        rvReport.setLayoutManager(new LinearLayoutManager(getContext()));
        reportAdapter = new ReportAdapter();
        reportAdapter.setCallback(this);
        mPresenter.loadReportList(UserInfo.get(MyApplication.getInstance()).getUserId());
    }

    //这一步卡了很久 想想最后还是放在io线程里执行了 否则无法setAdapter
    @Override
    public void setAdapter() {
        rvReport.setAdapter(reportAdapter);
    }

    @Override
    protected ReportPresenterImpl createPresenter() {
        return new ReportPresenterImpl(this);
    }

    //绑定数据到adapter
    @Override
    public void bindListData(List<Report> reportList) {
        reportAdapter.setReportList(reportList);
    }
    /*****************************************响应事件*********************************************/


    /**************************************其他工具函数********************************************/
    @Override
    public void notifyDataChanged() {
        reportAdapter.notifyDataSetChanged();
    }

}