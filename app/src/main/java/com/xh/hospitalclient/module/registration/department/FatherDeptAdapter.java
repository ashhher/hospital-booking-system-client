package com.xh.hospitalclient.module.registration.department;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseViewHolder;
import com.xh.hospitalclient.model.DeptBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FatherDeptAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "FatherDeptAdapter";

    private Context context;
    private List<String> fatherList;
    private FatherDeptCallback fatherDeptCallback;
    private List<Boolean> isClick;//标记哪个正在被点击
    private boolean flag;

    public FatherDeptAdapter() {
        flag = true;
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) {//设置上下文环境
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_dept,viewGroup,false);

        //仅第一次加载时初始化
        if(flag) {
            isClick = new ArrayList<>();
            isClick.add(true);
            for(int h = 1;h<getItemCount();h++) {
                isClick.add(false);
            }
            flag = false;
        }
        return new FatherDeptAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        if (fatherList != null && fatherList.size() > 0) {
            return fatherList.size();
        } else {
            return 1;
        }
    }

    void setFatherList(List<String> fatherList) {
        this.fatherList = fatherList;
    }

    public void setCallback(FatherDeptCallback fatherDeptCallback) {
        this.fatherDeptCallback = fatherDeptCallback;
    }

    public interface FatherDeptCallback {
        void setDeptList(String father);
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_dept)
        TextView tvDept;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(final int position) {
            super.onBind(position);
            final String father = fatherList.get(position);
            tvDept.setText(father);

            //设置点击后字体颜色
            if(isClick.get(position)) {
                tvDept.setTextColor(context.getResources().getColor(R.color.GreenLight));
            }else {
                tvDept.setTextColor(context.getResources().getColor(R.color.color_101));
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int h = 0;h<getItemCount();h++) {
                        isClick.set(h,false);
                    }
                    isClick.set(position,true);
                    fatherDeptCallback.setDeptList(father);
                    notifyDataSetChanged();
                    Log.i(TAG, "onClick: " + father);
                }
            });
        }

        @Override
        protected void clear() {
            tvDept.setText("");
        }
    }
}
