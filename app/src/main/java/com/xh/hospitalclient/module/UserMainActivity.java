package com.xh.hospitalclient.module;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.module.home.HomeFragment;
import com.xh.hospitalclient.module.report.ReportFragment;
import com.xh.hospitalclient.module.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UserMainActivity extends AppCompatActivity {
    private static final String TAG = "UserMainActivity";

    private  BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private ReportFragment reportFragment;
    private UserFragment userFragment;
    private Fragment[] fragments;
    private int lastfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        BottomNavigationView navView = (BottomNavigationView)findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_report, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

//    private void initFragment()
//    {
//
//        homeFragment = new HomeFragment();
//        reportFragment = new ReportFragment();
//        userFragment = new UserFragment();
//        fragments = new Fragment[]{homeFragment,reportFragment,userFragment};
//        lastfragment=0;
//        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,homeFragment).show(homeFragment).commit();
//        bottomNavigationView=(BottomNavigationView)findViewById(R.id.nav_view);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//    }
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    if(lastfragment != 0) {
//                        switchFragment(lastfragment,0);
//                        lastfragment = 0;
//                    }
//                    return true;
//                case R.id.navigation_report:
//                    if(lastfragment != 1) {
//                        switchFragment(lastfragment,1);
//                        lastfragment = 1;
//                    }
//                    return true;
//                case R.id.navigation_user:
//                    if(lastfragment != 2) {
//                        switchFragment(lastfragment,2);
//                        lastfragment = 2;
//                    }
//                    return true;
//            }
//            return false;
//        }
//
//    };
//
//    private void switchFragment(int lastfragment,int index)
//    {
//        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
//        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
//        Log.i(TAG, "switchFragment: hide " + index);
//        if(fragments[index].isAdded()==false)
//        {
//            transaction.add(R.id.nav_host_fragment,fragments[index]);
//        }
//        transaction.show(fragments[index]).commitAllowingStateLoss();
//        Log.i(TAG, "switchFragment: show " + index);
//    }
}
