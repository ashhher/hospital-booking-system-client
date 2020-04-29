package com.xh.hospitalclient.base;

import android.os.Bundle;
import android.view.KeyEvent;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xh.hospitalclient.widget.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

//继承RxAppCompatActivity：使用rxLifeCycle防止因rxjava引起的内存泄漏
public abstract class BaseActivity<V, T extends BaseActivityPresenter<V>>
        extends RxAppCompatActivity
        implements BaseView{

    protected T mPresenter;
    //用于Butterknife后续解绑
    protected Unbinder unbinder;

    //用于绑定布局文件
    protected abstract int getLayoutId();

    //一般情况下子类无需重写onCreate方法
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.onAttach((V)this);//绑定view
        setContentView(getLayoutId());//绑定布局文件
        unbinder = ButterKnife.bind(this);//Butterknife绑定界面组件
    }

    protected abstract T createPresenter();

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {
        ToastUtil.showToast(msg + " 失败");
    }

    @Override
    public void showSuccess(String msg) {
        ToastUtil.showToast(msg + " 成功");
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
            mPresenter.onDetach();//view解绑，防止内存泄漏
        }
        unbinder.unbind();//Butterknife解绑
        super.onDestroy();
    }
}
