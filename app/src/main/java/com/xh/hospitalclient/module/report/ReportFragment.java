package com.xh.hospitalclient.module.report;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseFragment;
import com.xh.hospitalclient.model.ReportBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.RealmResults;

public class ReportFragment
        extends BaseFragment<ReportContract.ReportView, ReportPresenterImpl>
        implements ReportContract.ReportView,ReportAdapter.ReportCallBack{
    private static final String TAG = "ReportFragment";

    @BindView(R.id.rv_report)
    RecyclerView rvReport;

    private ReportAdapter reportAdapter;

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
        mPresenter.loadReportList("111");//Todo: 数据交互
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

    @Override
    public void onReportItemClick() {
        showSuccess(TAG + " click");
    }//Todo: 跳转界面

    //绑定数据到adapter
    @Override
    public void bindListData(List<ReportBean> reportList) {
        reportAdapter.setReportList(reportList);
        reportAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyDataChanged() {
        reportAdapter.notifyDataSetChanged();
    }

}