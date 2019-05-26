package com.example.grassroots;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.grassroots.fragment.OfficeExpFragment;
import com.example.grassroots.fragment.OverviewFragment;
import com.example.grassroots.fragment.VotePositionFragment;

public class CongressTabActivity extends AppCompatActivity {

     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_congress_tab);

         SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
         ViewPager viewPager = findViewById(R.id.view_pager);
         viewPager.setAdapter(sectionsPagerAdapter);

         TabLayout tabs = findViewById(R.id.tabs);
         tabs.setupWithViewPager(viewPager);

     }
}
