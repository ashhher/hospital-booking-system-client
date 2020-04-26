package com.xh.hospitalclient.module.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseActivity;
import com.xh.hospitalclient.base.BasePresenter;
import com.xh.hospitalclient.module.Test.TestActivity;
import com.xh.hospitalclient.widget.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.LoginView, LoginPresenterImpl> implements LoginContract.LoginView {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.til_username)
    public TextInputLayout til_username;
    @BindView(R.id.til_password)
    public TextInputLayout til_password;
    @BindView(R.id.btn_login)
    public Button btn_login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl(this);
    }

    @OnClick(R.id.btn_login)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String username = til_username.getEditText().getText().toString();
                String password = til_password.getEditText().getText().toString();

                til_username.setErrorEnabled(false);
                til_password.setErrorEnabled(false);

                if(checkUsername(username) && checkPassword(password)) {
                    mPresenter.login(username,password);
                }
                break;
        }
    }

    private boolean checkUsername(String username) {
        if(username.isEmpty()){
            showError(til_username,"用户名不能为空");
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password) {
        if (password.isEmpty()) {
            showError(til_password,"密码不能为空");
            return false;
        }
        if (password.length() < 6 || password.length() > 18) {
            showError(til_password,"密码长度为6-18位");
            return false;
        }
        return true;
    }

    private void showError(TextInputLayout textInputLayout,String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    @Override
    public void showLogin(String info) {
        ToastUtil.showToast(info);
    }

    @Override
    public void toMainActivity() {
        Intent it_login_to_register = new Intent(this, TestActivity.class);
        startActivity(it_login_to_register);
    }
}
