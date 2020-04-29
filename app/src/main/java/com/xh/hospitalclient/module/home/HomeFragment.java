package com.xh.hospitalclient.module.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.xh.hospitalclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.tv_home)
    TextView tvHome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        tvHome.setText("THIS IS HOME");
        return view;
    }
}