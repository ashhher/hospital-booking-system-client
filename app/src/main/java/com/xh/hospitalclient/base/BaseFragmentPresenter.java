package com.xh.hospitalclient.base;

import android.util.Log;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;

import java.lang.ref.WeakReference;

import static android.content.ContentValues.TAG;

//与BaseActivityPresenter类似
public abstract class BaseFragmentPresenter<V>{
    protected WeakReference<V> mViewRef;

    private LifecycleProvider<FragmentEvent> mProvider;

    public BaseFragmentPresenter(LifecycleProvider<FragmentEvent> provider) {
        this.mProvider = provider;
    }

    public LifecycleProvider<FragmentEvent> getProvider() {
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