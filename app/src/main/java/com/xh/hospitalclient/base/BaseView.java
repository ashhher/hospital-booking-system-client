package com.xh.hospitalclient.base;

public interface BaseView {
    void showLoading();
    void hideLoading();
    void showError(String msg);
    void showSuccess(String msg);

}
