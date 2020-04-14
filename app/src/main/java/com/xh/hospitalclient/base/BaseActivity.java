package com.xh.hospitalclient.base;

import android.os.Bundle;
import android.view.KeyEvent;


import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xh.hospitalclient.widget.ToastUtil;

public abstract class BaseActivity<V, T extends BasePresenter<V>>
        extends RxAppCompatActivity
        implements BaseView{

    protected T mPresenter;

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initEvent();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.onAttach((V)this);
        setContentView(getLayoutId());
        initView();
        initData();
        initEvent();
    }

    protected abstract T createPresenter();

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return checkBackAction() || super.onKeyDown(keyCode, event);
    }
    //双击退出相关
    private boolean mFlag = false;
    private long mTimeout = -1;
    private boolean checkBackAction() {
        long time = 3000L;//判定时间设为3秒
        boolean flag = mFlag;
        mFlag = true;
        boolean timeout = (mTimeout == -1 || (System.currentTimeMillis() - mTimeout) > time);
        if (mFlag && (mFlag != flag || timeout)) {
            mTimeout = System.currentTimeMillis();
            ToastUtil.showToast("再点击一次回到桌面");
            return true;
        }
        return !mFlag;
    }

    @Override
    protected void onDestroy() {
        if(mPresenter != null) {
            mPresenter.onDetach();
        }
        super.onDestroy();
    }
}
