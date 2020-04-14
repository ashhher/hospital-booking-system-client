package com.xh.hospitalclient.utils;

import android.widget.Toast;

import com.xh.hospitalclient.MyApplication;

public class ToastUtil {
    private static Toast toast;
    public static void showToast(String text) {
        if(MyApplication.getInstance() != null) {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.getInstance(), text, Toast.LENGTH_SHORT);
            } else {
                toast.setText(text);
            }
            toast.show();
        }
    }
}
