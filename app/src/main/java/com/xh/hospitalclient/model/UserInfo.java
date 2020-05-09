package com.xh.hospitalclient.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class UserInfo {
    private static final String TAG = "UserInfo";
    private static final String FILE_NAME = "userInfo";
    private static User defalt = new User();

    public static void set(Context context, User user) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userId",user.getUserId());
        editor.putString("userName",user.getUserName());
        editor.putInt("userAge",user.getUserAge());
        editor.putBoolean("userSex",user.getUserSex());
        Log.i(TAG, "set: " + user);
        editor.apply();
    }

    public static User get(Context context) {
        defalt.setUserId("--");
        defalt.setUserName("--");
        defalt.setUserPwd("--");
        defalt.setUserAge(0);
        defalt.setUserSex(false);

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        User user = new User();
        user.setUserId(sp.getString("userId",defalt.getUserId()));
        user.setUserName(sp.getString("userName",defalt.getUserName()));
        user.setUserAge(sp.getInt("userAge",defalt.getUserAge()));
        user.setUserSex(sp.getBoolean("userSex",defalt.getUserSex()));
        user.setUserPwd("***");
        Log.i(TAG, "get: " + user);
        return user;
    }
}
