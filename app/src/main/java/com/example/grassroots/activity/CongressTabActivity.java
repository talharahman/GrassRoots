package com.example.grassroots.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.grassroots.R;
import com.example.grassroots.utils.SectionsPagerAdapter;
import com.example.grassroots.model.ProPublica.Members.CongressMember;
import com.example.grassroots.utils.CongressOverviewVM;

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
