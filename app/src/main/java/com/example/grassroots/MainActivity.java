package com.example.grassroots;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.grassroots.fragment.CongressFragment;
import com.example.grassroots.fragment.RepresentativeDirectoryFragment;
import com.example.grassroots.model.CivicInfoModel;
import com.example.grassroots.network.CivicInfoListener;
import com.example.grassroots.network.CivicInfoRetrofit;
import com.example.grassroots.network.CongressListener;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "findme";
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        sendToCongressFragment();

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void sendToRepresentativeDirectoryFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container, new RepresentativeDirectoryFragment())
                .commit();
    }

    public void sendToCongressFragment(){
        CongressFragment congressFragment = CongressFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container, congressFragment)
                .commit();
    }

}
