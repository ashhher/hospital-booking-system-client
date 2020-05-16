package com.xh.hospitalclient.module.registration.doctor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseViewHolder;
import com.xh.hospitalclient.model.Schedule;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrSchAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "DrSchAdapter";

    private Context context;
    private List<Schedule> scheduleList;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) {//设置上下文环境
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_dr_sch,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        if (scheduleList != null && scheduleList.size() > 0) {
            return scheduleList.size();
        } else {
            return 0;
        }
    }

    void setScheduleListList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_sch_date)
        TextView tvSchDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final Schedule schedule = scheduleList.get(position);

            tvSchDate.setText(schedule.getSchDate());
        }

        @Override
        protected void clear() {
            tvSchDate.setText("");
        }
    }
}
