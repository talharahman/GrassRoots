package com.example.grassroots;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.grassroots.fragment.OfficeExpFragment;
import com.example.grassroots.fragment.OfficeExpUIListener;
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
//    public void toOfficeExpenseFragment(String member_id, String year, String quarter) {
//        OfficeExpFragment officeExpFragment = new OfficeExpFragment()
//                .getParentFragment()
//                .replace(R.id.container_oe, officeExpFragment)
//                .addToBackStack(null)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .commit();
//    }

//    @Override
//    public void updateOfficeExpenseRecords(String member_id, String year, String quarter) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container_oe, officeExpFragment)
//                .addToBackStack(null)
//                .commit();
//    }
}
