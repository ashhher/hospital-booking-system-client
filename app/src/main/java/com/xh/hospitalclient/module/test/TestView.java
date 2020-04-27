package com.xh.hospitalclient.module.test;

import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.TestBean;

public interface TestView extends BaseView {
    void updateInfo(TestBean testBean);
}
