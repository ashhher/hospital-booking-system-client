package com.xh.hospitalclient.base;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.lang.ref.WeakReference;

public abstract class BaseActivityPresenter<V>{
    //view弱引用，防止内存泄漏
    protected WeakReference<V> mViewRef;
    //解决rxjava内存泄漏
    private LifecycleProvider<ActivityEvent> mProvider;

    //与BaseFragmentPresenter的唯一不同之处，因为此处需要传入ActivityEvent
    public BaseActivityPresenter(LifecycleProvider<ActivityEvent> provider) {
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