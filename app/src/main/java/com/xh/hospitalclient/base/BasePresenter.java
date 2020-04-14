package com.xh.hospitalclient.base;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V>{
    protected WeakReference<V> mViewRef;

    private LifecycleProvider<ActivityEvent> mProvider;

    public BasePresenter(LifecycleProvider<ActivityEvent> provider) {
        this.mProvider = provider;
    }

    public LifecycleProvider<ActivityEvent> getProvider() {
        return mProvider;
    }

    public void onAttach(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return isViewAttached() ? mViewRef.get() : null;
    }

    protected boolean isViewAttached() {
        return null != mViewRef && null != mViewRef.get();
    }

    public void onDetach() {
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}