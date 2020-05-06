package com.lb.learnsong;


import android.os.Bundle;
import android.view.Window;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lb.learnsong.ui.BaseLsActivity;
import com.lb.learnsong.ui.lsview.BackView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseLsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

    }

}
