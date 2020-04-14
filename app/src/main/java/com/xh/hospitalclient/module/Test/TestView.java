package com.xh.hospitalclient.module.Test;

import com.xh.hospitalclient.base.BaseView;
import com.xh.hospitalclient.model.entities.TestBean;

public interface TestView extends BaseView {
    void updateInfo(TestBean testBean);
}
