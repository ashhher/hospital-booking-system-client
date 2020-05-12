package com.xh.hospitalclient.module.registration.schedule.ui.table;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.android.FragmentEvent;
import com.xh.hospitalclient.R;
import com.xh.hospitalclient.base.BaseFragment;
import com.xh.hospitalclient.model.Doctor;
import com.xh.hospitalclient.model.Schedule;
import com.xh.hospitalclient.module.registration.schedule.SchContract;
import com.xh.hospitalclient.module.registration.schedule.SchModelImpl;
import com.xh.hospitalclient.module.registration.schedule.SchPresenterImpl;
import com.xh.hospitalclient.net.RetrofitSubscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ScheduleFragment
        extends BaseFragment<SchContract.SchView, SchPresenterImpl>
        implements SchContract.SchView, DoctorAdapter.DoctorCallBack{
    private List<String> dateList;
    private List<Fragment> fragments;
    int deptId;

    @BindView(R.id.tab_date)
    TabLayout tabDate;
    @BindView(R.id.vp_date)
    ViewPager vpDate;

    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_table_schedule;
    }

    @Override
    protected void initView() {
        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle != null && bundle.getSerializable("departmentId") != null) {
            deptId = (int) bundle.getSerializable("departmentId");
        }
        mPresenter.initSchList(deptId);
    }

    @Override
    protected SchPresenterImpl createPresenter() {
        return new SchPresenterImpl(this);
    }

    @Override
    public void bindSchListData(List<String> date) {
        dateList = new ArrayList<>();
        fragments = new ArrayList<>();

        for(int j = 0;j<date.size();j++) {
            dateList.add(date.get(j).substring(5));
        }

        for (int i = 0; i<date.size();i++) {
            ContentFragment fragment = ContentFragment.newInstance(date.get(i),deptId);
            fragments.add(fragment);
        }

        for (int i=0;i<dateList.size();i++){
            tabDate.addTab(tabDate.newTab().setText(dateList.get(i)));//添加tab选项
        }

        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }
            //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
            @Override
            public CharSequence getPageTitle(int position) {
                return dateList.get(position);
            }
        };

        vpDate.setAdapter(mAdapter);
        tabDate.setupWithViewPager(vpDate);//将TabLayout和ViewPager关联起来。
        tabDate.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }

    @Override
    public void bindDrListData(List<Doctor> doctorList) {

    }

    @Override
    public void notifyDataChanged() {

    }

    @Override
    public void setAdapter() {

    }
}
