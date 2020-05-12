package com.xh.hospitalclient.module.registration.schedule.ui.table;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseViewHolder;
import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.utils.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoctorAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "DoctorAdapter";

    private Context context;
    private List<Doctor> doctorList;
    private DoctorCallBack doctorCallBack;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) {//设置上下文环境
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        if (doctorList != null && doctorList.size() > 0) {
            return doctorList.size();
        } else {
            return 0;
        }
    }

    void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public void setCallback(DoctorCallBack doctorCallBack) {
        this.doctorCallBack = doctorCallBack;
    }

    public interface DoctorCallBack {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final Doctor doctor = doctorList.get(position);

            //加载圆形图片
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            GlideApp.with(context)
                    .load("https://upload.jianshu.io/users/upload_avatars/19737964/7a5e04af-a18e-47da-8f11-ad9461d60d80?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240")
                    .centerCrop()
                    .apply(requestOptions)
                    .into(ivAvatar);
//            Glide.with(context).load("https://upload.jianshu.io/users/upload_avatars/19737964/7a5e04af-a18e-47da-8f11-ad9461d60d80?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240").into(ivAvatar);//todo:改为url
            tvDrName.setText(doctor.getDrName());
            tvDrPos.setText(doctor.getDrPos());
            tvDrInfo.setText(doctor.getDrInfo());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent intent = new Intent();
//                    intent.putExtra("reportDetail",report);//此处要intent传递对象必须要使该bean implements Serializable
//                    intent.setClass(context, ReportDetailActivity.class);
//                    context.startActivity(intent);

                    Log.i(TAG, "onClick: doctor" + doctor.getDrName());
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
