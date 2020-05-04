package com.xh.hospitalclient.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.widget.LoadingDialog;
import com.xh.hospitalclient.widget.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

//与BaseActivity类似
public abstract class BaseFragment<V, T extends BaseFragmentPresenter<V>>
        extends RxFragment
        implements BaseView{

    protected T mPresenter;
    protected Unbinder unbinder;
    protected LoadingDialog loadingDialog;

    protected abstract int getLayoutId();
    protected abstract void initView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this,view);
        mPresenter = createPresenter();
        mPresenter.onAttach((V)this);
        initView();
        return view;
    }

    protected abstract T createPresenter();

    @Override
    public void showLoading() {
        loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.close();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        if(mPresenter != null) {
            mPresenter.onDetach();
        }
        super.onDestroy();
    }

}
