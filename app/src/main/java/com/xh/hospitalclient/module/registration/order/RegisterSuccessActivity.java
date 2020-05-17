package com.xh.hospitalclient.module.registration.order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.module.UserMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterSuccessActivity extends AppCompatActivity {
    @BindView(R.id.tv_rank)
    TextView tvRank;
    @BindView(R.id.tv_back)
    TextView tvBack;

    int rank = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("rank") != null) {
            rank = (int) bundle.getSerializable("rank");
        }
        tvRank.setText(rank+"号");
    }

    @OnClick({R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                Intent it_login_to_main = new Intent(this, UserMainActivity.class);
                startActivity(it_login_to_main);
                break;
        }
    }
}
