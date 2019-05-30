package com.example.grassroots.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.grassroots.R;
import com.example.grassroots.fragment.petition.PetitionFirstFragment;
import com.example.grassroots.utils.PetitionFragmentsListener;

public class PetitionHostActivity extends AppCompatActivity implements PetitionFragmentsListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petition_host);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.petition_container, new PetitionFirstFragment())
                .addToBackStack(null)
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bot_nav_action:
                Intent petitionIntent = new Intent(this, PetitionHostActivity.class);
                startActivity(petitionIntent);
                return true;
            case R.id.bot_nav_event:
                // TODO provision for Events UI
                return true;
            case R.id.bot_nav_contact:
                Intent contactIntent = new Intent(this, LocalRepsActivity.class);
                contactIntent.putExtra("ZIP", "11101");
                startActivity(contactIntent);
                return true;
            case R.id.bot_nav_search:
                Intent searchIntent = new Intent(this, CongressActivity.class);
                startActivity(searchIntent);
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
    public void moveToSharePetition(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.petition_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .addToBackStack(null)
                .commit();
    }

}
