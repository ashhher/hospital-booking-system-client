package com.xh.hospitalclient.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.widget.View.LVCircularRing;

public class LoadingDialog {
    LVCircularRing mLoadingView;
    Dialog mLoadingDialog;

    public LoadingDialog(Context context) {

        View view = LayoutInflater.from(context).inflate(
                R.layout.loading_dialog_view, null);

        LinearLayout layout = view.findViewById(R.id.dialog_view);

        mLoadingView = view.findViewById(R.id.lv_circularring);

        mLoadingDialog = new Dialog(context, R.style.loading_dialog);

        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }

    public void show(){
        mLoadingDialog.show();
        mLoadingView.startAnim();
    }

    public void close(){
        if (mLoadingDialog!=null) {
            mLoadingView.stopAnim();
            mLoadingDialog.dismiss();
            mLoadingDialog=null;
        }
    }
}