package com.example.grassroots.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.grassroots.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "findme";
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setReferences();
        initialize();
    //    moveToListOfPetitions(new MainFeed());
    }

    private void initialize() {
        setSupportActionBar(toolbar);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawer.openDrawer(Gravity.START);
        }
        return true;
    }

    private void setReferences() {
        toolbar = findViewById(R.id.toolbar_local_reps);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }

    private void getLocaleDialog() {
        AlertDialog.Builder getLocale = new AlertDialog.Builder(this);
        View dialogLayout = getLayoutInflater().inflate(R.layout.local_alert_dialog, null);
        EditText localeText = dialogLayout.findViewById(R.id.localeText);
        Button submitButton = dialogLayout.findViewById(R.id.submit_button);

        getLocale.setView(dialogLayout);
        final AlertDialog alertDialog = getLocale.create();
        submitButton.setOnClickListener(v -> {
            alertDialog.dismiss();
            // TODO do networkCall again
        });
        alertDialog.show();
    }

}