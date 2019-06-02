package com.example.grassroots.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.grassroots.R;
import com.example.grassroots.fragment.petition.PetitionFirstFragment;
import com.example.grassroots.utils.NewPetitionFragmentsListener;

public class NewPetitionHostActivity extends AppCompatActivity implements NewPetitionFragmentsListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petition_host);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.petition_container, new PetitionFirstFragment())
                .addToBackStack(null)
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_petition);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_petition_home);
        floatingActionButton.setOnClickListener(v -> {
            Intent home = new Intent(getApplicationContext(), MainDashboard.class);
            startActivity(home);
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bot_add_action:
                Intent action = new Intent(this, NewPetitionHostActivity.class);
                startActivity(action);
                return true;
            case R.id.bot_view_activity:
                Intent view = new Intent(this, UserViewActivity.class);
                startActivity(view);
                return true;
            case R.id.bot_nav_contact:
                Intent contact = new Intent(this, LocalRepsActivity.class);
                startActivity(contact);
                return true;
            case R.id.bot_nav_search:
                Intent search = new Intent(this, CongressActivity.class);
                startActivity(search);
                return true;
        }
        return true;
    }

    @Override
    public void moveToPetitionSecondPart(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.petition_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToPetitionThirdPart(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.petition_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToPetitionPreview(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.petition_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToPetitionUpdatesFirstFrgament(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToPetitionUpdatesSecondFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                .addToBackStack(null)
                .commit();
    }
  /*  @Override
    public void moveToSharePetition(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.petition_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .addToBackStack(null)
                .commit();
    }*/

}
