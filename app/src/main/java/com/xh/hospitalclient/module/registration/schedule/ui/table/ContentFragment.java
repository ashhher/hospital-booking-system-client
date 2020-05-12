package com.xh.hospitalclient.module.registration.schedule.ui.table;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.model.Schedule;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmResults;


public class ContentFragment extends Fragment {
    private static final String TAG = "ContentFragment";
    private List<Schedule> schedules;
    private ScheduleAdapter scheduleAdapter;

    @BindView(R.id.rv_reg)
    RecyclerView rvReg;

    public static ContentFragment newInstance(String date,int deptId){
        ContentFragment fragment = new ContentFragment();

        Bundle bundle = new Bundle();
        bundle.putString("date",date);
        bundle.putInt("deptId",deptId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_content,null);
        ButterKnife.bind(this,view);

        String date = getArguments().getString("date");
        int deptId = getArguments().getInt("deptId");

        Realm realm = Realm.getDefaultInstance();
        schedules = realm.where(Schedule.class)
                .equalTo("schDate",date)
                .equalTo("deptId",deptId)
                .findAll();
        Log.i(TAG, "从realm读取shchedules列表: "+ schedules.toString());
        rvReg.setLayoutManager(new LinearLayoutManager(getContext()));
        scheduleAdapter = new ScheduleAdapter();
        scheduleAdapter.setScheduleListList(schedules);
        rvReg.setAdapter(scheduleAdapter);
        rvReg.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        return view;
    }


}
