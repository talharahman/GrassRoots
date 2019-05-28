package com.example.grassroots.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.grassroots.R;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bot_nav_action:
                //inflateFragment(createPetitionFragment)
                return true;
            case R.id.bot_nav_event:
                // TODO provision for Events UI
                return true;
            case R.id.bot_nav_contact:
                /*
                  inflateFragment(new BillsFragment());
                  Bills Fragment now deprecated, refer to tab contained in Congress Fragment

                  inflateFragment(LocalRepsFragment.newInstance("11355"));
                */
                Intent contactIntent = new Intent(this, LocalRepsActivity.class);
                contactIntent.putExtra("ZIP", "11101");
                startActivity(contactIntent);
                return true;
            case R.id.bot_nav_search:
                /*
                  need to fix searchbox disappearing for fragment input,
                  otherwise keep as Activity

                  toolbar.setVisibility(View.GONE);
                  inflateFragment(new CongressFragment());
                 */
                Intent searchIntent = new Intent(this, CongressActivity.class);
                startActivity(searchIntent);
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