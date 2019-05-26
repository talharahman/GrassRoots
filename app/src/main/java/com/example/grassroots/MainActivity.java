package com.example.grassroots;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.example.grassroots.fragment.BillsFragment;
import com.example.grassroots.fragment.CongressFragment;
import com.example.grassroots.fragment.LocalRepsFragment;
import com.example.grassroots.fragment.petition.PetitionFirstFragment;
import com.example.grassroots.fragment.petition.PetitionFragmentsListener;
import com.example.grassroots.fragment.petition.PetitionsListFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PetitionFragmentsListener {

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
        moveToListOfPetitions(new PetitionsListFragment());
    }

    private void initialize() {
        setSupportActionBar(toolbar);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_action:
                moveToPetitionFirstPart(new PetitionFirstFragment());
                //inflateFragment(createPetitionFragment)
                return true;
            case R.id.nav_contact:
                getLocaleDialog();
                return true;
            case R.id.nav_bills:
                inflateFragment(new BillsFragment());
                return true;
            case R.id.nav_search:
                inflateFragment(new CongressFragment());
                return true;
        }
        return true;
    }

    public void inflateFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                .addToBackStack(null)
                .commit();
    }

    private void setReferences(){
        toolbar = findViewById(R.id.toolbar);
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
            inflateFragment(LocalRepsFragment.newInstance(localeText.getText().toString()));
        });
        alertDialog.show();
    }

    @Override
    public void moveToPetitionFirstPart(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToPetitionSecondPart(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToPetitionThirdPart(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToPetitionPreview(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToSharePetition(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                .addToBackStack(null)
                .commit();
    }
    @Override
    public void moveToListOfPetitions(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                .addToBackStack(null)
                .commit();
    }
}