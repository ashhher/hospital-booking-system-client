package com.xh.hospitalclient.module.login.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.module.UserMainActivity;
import com.xh.hospitalclient.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterContract.RegisterView, RegisterPresenterImpl> implements RegisterContract.RegisterView {
    private static final String TAG = "RegisterActivity";

    @BindView(R.id.til_rusername)
    public TextInputLayout til_rusername;
    @BindView(R.id.til_rpassword)
    public TextInputLayout til_rpassword;
    @BindView(R.id.til_repeat)
    public TextInputLayout til_repeat;
    @BindView(R.id.til_name)
    public TextInputLayout til_name;
    @BindView(R.id.til_age)
    public TextInputLayout til_age;
    @BindView(R.id.til_sex)
    public TextInputLayout til_sex;
    @BindView(R.id.btn_register)
    public Button btn_register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected RegisterPresenterImpl createPresenter() {
        return new RegisterPresenterImpl(this);
    }

    @OnClick(R.id.btn_register)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String username = til_rusername.getEditText().getText().toString();
                String password = til_rpassword.getEditText().getText().toString();
                String repeat = til_repeat.getEditText().getText().toString();
                String name = til_name.getEditText().getText().toString();
                String age = til_age.getEditText().getText().toString();
                String sex = til_sex.getEditText().getText().toString();

                til_rusername.setErrorEnabled(false);
                til_rpassword.setErrorEnabled(false);
                til_repeat.setErrorEnabled(false);
                til_name.setErrorEnabled(false);
                til_age.setErrorEnabled(false);
                til_sex.setErrorEnabled(false);

                if(check(username,password,repeat,name,sex,age)) {
                    Log.i(TAG, "onClick: check success");
                    mPresenter.register(username,password,name,age,sex);
                }
                break;
        }
    }

    private boolean check(String username, String password, String repeat, String name, String age, String sex) {
        if(username.isEmpty()){
            showError(til_rusername,"用户名不能为空");
            return false;
        }
        if(password.isEmpty()){
            showError(til_rpassword,"密码不能为空");
            return false;
        }
        if(!repeat.equals(password)){
            showError(til_repeat,"重复密码需一致");
            return false;
        }
        if(name.isEmpty()){
            showError(til_name,"姓名不能为空");
            return false;
        }
        if(age.isEmpty()){
            showError(til_age,"年龄不能为空");
            return false;
        }
        if(sex.isEmpty()){
            showError(til_sex,"性别不能为空");
            return false;
        }
        else if(!sex.equals("男") && !sex.equals("女")) {
            showError(til_sex,"请填写“男”或“女”");
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
        Intent it_register_to_main = new Intent(this, UserMainActivity.class);
        startActivity(it_register_to_main);
    }
}
