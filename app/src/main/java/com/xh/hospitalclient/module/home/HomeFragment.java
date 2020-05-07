package com.xh.hospitalclient.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.module.registration.department.ui.DeptActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {
    @BindView(R.id.btn_dept)
    Button btnDept;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
//        tvHome.setText("THIS IS HOME");
        return view;
    }

    @OnClick(R.id.btn_dept)
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), DeptActivity.class));
    }
}