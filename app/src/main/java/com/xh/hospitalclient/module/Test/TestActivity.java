package com.xh.hospitalclient.module.Test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.model.entities.TestBean;
import com.xh.hospitalclient.module.Test.TestPresenter;
import com.xh.hospitalclient.module.Test.TestView;
import com.xh.hospitalclient.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity<TestView,TestPresenter> implements TestView {
    private static final String TAG = "TestActivity";

    @BindView(R.id.btn)
    public Button bt;
    @BindView(R.id.tvResult)
    public TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter(this);
    }

    @OnClick(R.id.btn)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                Log.i(TAG, "onClick: btn");
                mPresenter.getInfo("1");
                break;
        }
    }
    @Override
    public void updateInfo(TestBean testBean) {
        tv.setText(String.valueOf("请求成功：" + testBean.toString()));
    }
}
