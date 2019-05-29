package com.example.grassroots;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.grassroots.fragment.OfficeExpFragment;
import com.example.grassroots.fragment.OfficeExpFragmentListener;
import com.example.grassroots.fragment.OverviewFragment;
import com.example.grassroots.fragment.SpinnerOEFragment;
import com.example.grassroots.fragment.VotePositionFragment;
import com.example.grassroots.model.ProPublica.Members.CongressMember;

public class CongressTabActivity extends AppCompatActivity  {

    private CongressOverviewVM congressOverviewVM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congress_tab);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        congressOverviewVM = ViewModelProviders.of(this).get(CongressOverviewVM.class);
        CongressMember congressMember = (CongressMember) getIntent().getSerializableExtra("CONGRESSMEMBER");
        congressOverviewVM.setCongressMember(congressMember);
        Log.d("VIEWMODELTROUBLESHOOT", "onCreate: " + congressMember.getShort_title());
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
