package com.xh.hospitalclient.module.report;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseViewHolder;
import com.xh.hospitalclient.model.ReportBean;

import java.util.List;

import javax.security.auth.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;


public class ReportAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "ReportAdapter";

    private Context context;
    private List<ReportBean> reportBeanList;
    private ReportCallBack reportCallBack;

//    public ReportAdapter(List<ReportBean> reportList) {
//        reportBeanList = reportList;
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
        if (reportBeanList != null && reportBeanList.size() > 0) {
            return reportBeanList.size();
        } else {
            return 1;
        }
    }

    public void setCallback(ReportCallBack reportCallBack) {
        this.reportCallBack = reportCallBack;
    }

    void setReportList(List<ReportBean> reportList) {
        reportBeanList = reportList;
    }

    //callback函数，在fragment中实现
    public interface ReportCallBack {
        void onReportItemClick();
    }

    public class ViewHolder extends BaseViewHolder {
        CardView cardView;
        @BindView(R.id.tv_rpt_title)
        TextView tvRptTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final ReportBean report = reportBeanList.get(position);
            tvRptTitle.setText(report.getRptTitle());

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Log.i(TAG, "onClick: item");
//                }
//            });
        }

        @OnClick(R.id.rpt_item)
        void onItemClick() {
            reportCallBack.onReportItemClick();
        }

        @Override
        protected void clear() {
            tvRptTitle.setText("");
        }
    }
}
