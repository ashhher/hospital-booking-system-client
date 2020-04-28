package com.xh.hospitalclient.module.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportFragment
        extends BaseFragment<ReportContract.ReportView, ReportPresenterImpl>
        implements ReportContract.ReportView{

    @BindView(R.id.tv_report)
    public TextView tv_report;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_report;
    }

    @Override
    protected ReportPresenterImpl createPresenter() {
        return new ReportPresenterImpl(this);
    }


//    private ReportViewModel reportViewModel;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        reportViewModel =
//                ViewModelProviders.of(this).get(ReportViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_report, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        reportViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
//    }
}