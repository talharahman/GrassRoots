package com.example.grassroots;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.grassroots.fragment.OfficeExpUIListener;
import com.example.grassroots.model.ProPublica.Members.CongressMember;
import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpResult;
import com.example.grassroots.utils.CongressPagerAdapter;

public class CongressTabActivity extends AppCompatActivity  {

    private CongressOverviewVM congressOverviewVM;
    private CongressExpenseVM congressExpenseVM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congress_tab);

        CongressPagerAdapter sectionsPagerAdapter = new CongressPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        congressOverviewVM = ViewModelProviders.of(this).get(CongressOverviewVM.class);
        CongressMember congressMember = (CongressMember) getIntent().getSerializableExtra("CONGRESSMEMBER");
        congressOverviewVM.setCongressMember(congressMember);
        Log.d("VIEWMODELTROUBLESHOOT", "onCreate: " + congressMember.getShort_title());
//
//        congressExpenseVM = ViewModelProviders.of(this).get(CongressExpenseVM.class);
//        OfficeExpResult expResult = (OfficeExpResult) getIntent().getSerializableExtra("OFFICERESULT");
//        congressExpenseVM.setOfficeExpResult(expResult);
//        Log.d("SECONDVMPASSINGTHROUGH", "onCreate: " + expResult.getCategory());

    }
}
