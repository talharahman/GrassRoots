package com.example.grassroots.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.grassroots.R;
import com.example.grassroots.fragment.BillsFragment;
import com.example.grassroots.fragment.LocalRepsFragment;

public class MainDashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        initialize();
    }

    private void initialize() {
       /* Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);*/

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bot_nav_action:
                //inflateFragment(createPetitionFragment)
                return true;
            case R.id.bot_nav_contact:
                inflateFragment(LocalRepsFragment.newInstance("11355"));
                return true;
            case R.id.bot_nav_bills:
                inflateFragment(new BillsFragment());
                return true;
            case R.id.bot_nav_search:
                //inflateFragment(new CongressFragment());
                Intent intent = new Intent(this, CongressActivity.class);
                startActivity(intent);
                return true;
        }
        return true;
    }

    public void inflateFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .addToBackStack(null)
                .commit();
    }
}