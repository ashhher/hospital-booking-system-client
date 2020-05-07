package com.xh.hospitalclient.module;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.xh.hospitalclient.R;
import com.xh.hospitalclient.module.home.HomeFragment;
import com.xh.hospitalclient.module.report.ui.ReportFragment;
import com.xh.hospitalclient.module.user.UserFragment;

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

}
