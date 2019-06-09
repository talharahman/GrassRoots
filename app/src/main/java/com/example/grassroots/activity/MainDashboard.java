package com.example.grassroots.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.grassroots.R;
import com.example.grassroots.fragment.user.MainFeed;
import com.example.grassroots.model.user.UserActionViewModel;
import com.example.grassroots.utils.PetitionsFeedInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainDashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, PetitionsFeedInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        initialize();
        getFirebaseData();

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


    private void getFirebaseData() {


//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        currentUser.getUid();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth
                .signInWithEmailAndPassword("talharahman@pursuit.org", "password2")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("MYCURRENTID", "onComplete: " + firebaseAuth.getUid());
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bot_add_action:
                Intent action = new Intent(this, PetitionHostActivity.class);
                startActivity(action);
                return true;
            case R.id.bot_view_activity:
                Intent view = new Intent(this, UserActionActivity.class);
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

    @Override
    public void moveToPetitionAnim(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.feed_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToPetitionUpdatesFirstFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.feed_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToPetitionUpdatesSecondFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.feed_container, fragment)
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                .addToBackStack(null)
                .commit();
    }
}