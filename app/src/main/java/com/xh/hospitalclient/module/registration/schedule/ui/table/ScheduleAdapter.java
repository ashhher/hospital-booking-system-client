package com.xh.hospitalclient.module.registration.schedule.ui.table;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseViewHolder;
import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;
import com.xh.hospitalclient.module.registration.doctor.DoctorDetailActivity;
import com.xh.hospitalclient.module.registration.order.OrderActivity;
import com.xh.hospitalclient.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class ScheduleAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "ScheduleAdapter";

    private Context context;
    private List<Schedule> scheduleList;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) {//设置上下文环境
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_schedule,viewGroup,false);
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
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_dr_name)
        TextView tvDrName;
        @BindView(R.id.tv_dr_pos)
        TextView tvDrPos;
        @BindView(R.id.tv_dr_info)
        TextView tvDrInfo;
        @BindView(R.id.btn_reg)
        TextView btnReg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final Schedule schedule = scheduleList.get(position);

            //加载圆形图片
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            GlideApp.with(context)
                    .load("https://upload.jianshu.io/users/upload_avatars/19737964/7a5e04af-a18e-47da-8f11-ad9461d60d80?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240")
                    .centerCrop()
                    .apply(requestOptions)
                    .into(ivAvatar);

            List<Doctor> doctors = new ArrayList<>();
            final Realm realm = Realm.getDefaultInstance();
            doctors = realm.where(Doctor.class)
                    .equalTo("drId",schedule.getDrId())
                    .findAll();
            final Doctor doctor = doctors.get(0);
            tvDrName.setText(doctor.getDrName());
            tvDrPos.setText(doctor.getDrPos());
            tvDrInfo.setText(doctor.getDrInfo());
            realm.close();

            btnReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("schId",schedule.getSchId());
                    intent.setClass(context, OrderActivity.class);
                    context.startActivity(intent);
                    Log.i(TAG, "to register: " + schedule.toString());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.putExtra("drId",doctor.getDrId());
                    intent.setClass(context, DoctorDetailActivity.class);
                    context.startActivity(intent);
                    Log.i(TAG, "to doctor: " + doctor.getDrName());
                }
            });
        }

        @Override
        protected void clear() {
            ivAvatar.setBackgroundColor(555555);
            tvDrName.setText("");
            tvDrPos.setText("");
            tvDrInfo.setText("");
        }
    }
}
