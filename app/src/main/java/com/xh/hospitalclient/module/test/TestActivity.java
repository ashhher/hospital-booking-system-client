package com.xh.hospitalclient.module.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.model.TestBean;
import com.xh.hospitalclient.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity<TestView, TestActivityPresenter> implements TestView {
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
        return R.layout.activity_test;
    }

    @Override
    protected TestActivityPresenter createPresenter() {
        return new TestActivityPresenter(this);
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
