package com.example.grassroots.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.grassroots.R;
import com.example.grassroots.fragment.petition.DetailsPetitonFragment;
import com.example.grassroots.fragment.petition.MainFeed;
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
    public void moveToDetailsPetition(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.feed_container, new DetailsPetitonFragment())
                .addToBackStack(null)
                .commit();
    }
}