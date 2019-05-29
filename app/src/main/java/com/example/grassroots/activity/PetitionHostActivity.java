package com.example.grassroots.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.grassroots.R;
import com.example.grassroots.fragment.petition.PetitionFirstFragment;
import com.example.grassroots.utils.PetitionFragmentsListener;

public class PetitionHostActivity extends AppCompatActivity implements PetitionFragmentsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petition_host);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.petition_container, new PetitionFirstFragment())
                .addToBackStack(null)
                .commit();
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
