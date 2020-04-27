package com.xh.hospitalclient.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.module.UserMainActivity;
import com.xh.hospitalclient.base.BaseActivity;
import com.xh.hospitalclient.module.login.Register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity
        extends BaseActivity<LoginContract.LoginView, LoginPresenterImpl>
        implements LoginContract.LoginView {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.til_username)
    public TextInputLayout til_username;
    @BindView(R.id.til_password)
    public TextInputLayout til_password;
    @BindView(R.id.btn_login)
    public Button btn_login;
    @BindView(R.id.tv_register)
    public TextView tv_register;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl(this);
    }

    @OnClick({R.id.btn_login,R.id.tv_register})
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
            case R.id.tv_register:
                toRegisterAvtivity();

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
    public void toMainActivity() {
        Intent it_login_to_main = new Intent(this, UserMainActivity.class);
        startActivity(it_login_to_main);
    }

    public void toRegisterAvtivity() {
        Intent it_login_to_register = new Intent(this, RegisterActivity.class);
        startActivity(it_login_to_register);
    }
}
