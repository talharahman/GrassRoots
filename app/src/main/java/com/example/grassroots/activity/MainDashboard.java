package com.example.grassroots.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.grassroots.R;
import com.example.grassroots.fragment.petition.DetailsPetitionFragment;
import com.example.grassroots.fragment.user.MainFeed;
import com.example.grassroots.utils.PetitionsFeedInterface;

public class MainDashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, PetitionsFeedInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        initialize();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.feed_container, new MainFeed())
                .addToBackStack(null)
                .commit();
    }

    private void initialize() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bot_add_action:
                Intent action = new Intent(this, PetitionHostActivity.class);
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
    public void moveToDetailsPetition(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.feed_container, fragment)
                .addToBackStack(null)
                .commit();
    }

//        @Override
//    public void moveToListOfPetitions(Fragment fragment) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_container, fragment)
//                .setCustomAnimations(R.anim.enter, R.anim.exit)
//                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
//                .addToBackStack(null)
//                .commit();
//    }

    @Override
    public void moveToPetitionUpdatesFirstFragament(Fragment fragment) {
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


}