package com.xh.hospitalclient.module.report.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseViewHolder;
import com.xh.hospitalclient.model.Report;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReportAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "ReportAdapter";

    private Context context;
    private List<Report> reportList;
    private ReportCallBack reportCallBack;

//    public ReportAdapter(List<Report> reportList) {
//        reportList = reportList;
//    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) {//设置上下文环境
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_report,viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        baseViewHolder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (reportList != null && reportList.size() > 0) {
            return reportList.size();
        } else {
            return 0;
        }
    }

    public void setCallback(ReportCallBack reportCallBack) {
        this.reportCallBack = reportCallBack;
    }

    void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    //callback函数，在fragment中实现
    public interface ReportCallBack {
    }

    public class ViewHolder extends BaseViewHolder {
        CardView cardView;
        @BindView(R.id.tv_rpt_title)
        TextView tvRptTitle;
        @BindView(R.id.tv_rpt_date)
        TextView tvRptDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final Report report = reportList.get(position);
            tvRptTitle.setText(report.getRptTitle());
            tvRptDate.setText(report.getRptDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.putExtra("reportDetail",report);//此处要intent传递对象必须要使该bean implements Serializable
                    intent.setClass(context, ReportDetailActivity.class);
                    context.startActivity(intent);

                    Log.i(TAG, "onClick: item");
                }
            });
        }

//        @OnClick(R.id.rpt_item)
//        void onItemClick() {
//            reportCallBack.onReportItemClick();
//        }

        @Override
        protected void clear() {
            tvRptTitle.setText("");
            tvRptDate.setText("");
        }
    }
}
