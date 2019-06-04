package com.example.grassroots.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.grassroots.R;
import com.example.grassroots.utils.CongressPagerAdapter;
import com.example.grassroots.model.ProPublica.Members.CongressMember;
import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;

public class CongressTabActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congress_tab);

        initialize();
    }

    private void initialize() {
        CongressPagerAdapter congressPagerAdapter = new CongressPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(congressPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        CongressOverviewVM congressOverviewVM = ViewModelProviders.of(this).get(CongressOverviewVM.class);
        CongressMember congressMember = (CongressMember) getIntent().getSerializableExtra("CONGRESSMEMBER");
        congressOverviewVM.setCongressMember(congressMember);
        Log.d("VIEWMODELTROUBLESHOOT", "onCreate: " + congressMember.getShort_title());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_tab);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_tab_home);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), MainDashboard.class);
                startActivity(home);
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


//    @Override
//    public void toOfficeExpenseRecords(String member_id) {
//        OfficeExpFragment officeExpFragment = OfficeExpFragment.newInstance();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id., officeExpFragment)
//                .addToBackStack(null)
//                .commit();
//    }
}
