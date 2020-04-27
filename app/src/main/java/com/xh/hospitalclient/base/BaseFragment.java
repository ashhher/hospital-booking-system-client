package com.xh.hospitalclient.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.widget.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

//与BaseActivity类似
public abstract class BaseFragment<V, T extends BaseFragmentPresenter<V>>
        extends RxFragment
        implements BaseView{

    protected T mPresenter;
    protected Unbinder unbinder;

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mPresenter = createPresenter();
        View view = inflater.inflate(getLayoutId(),container);
        unbinder = ButterKnife.bind(this,view);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onAttach((V)this);
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
        ToastUtil.showToast(msg + " fail");
    }

    @Override
    public void showSuccess(String msg) {
        ToastUtil.showToast(msg + " success");
    }

    @Override
    public void onDestroy() {
        if(mPresenter != null) {
            mPresenter.onDetach();
        }
        unbinder.unbind();
        super.onDestroy();
    }

}
