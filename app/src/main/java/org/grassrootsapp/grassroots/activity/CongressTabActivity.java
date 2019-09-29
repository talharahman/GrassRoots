package org.grassrootsapp.grassroots.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.fragment.congress.TabContactListener;
import org.grassrootsapp.grassroots.utils.CongressPagerAdapter;
import org.grassrootsapp.grassroots.model.ProPublica.Members.CongressMember;
import org.grassrootsapp.grassroots.model.ProPublica.Members.CongressOverviewVM;

public class CongressTabActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, TabContactListener {


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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_tab);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_tab_home);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(CongressTabActivity.this.getApplicationContext(), MainDashboard.class);
                CongressTabActivity.this.startActivity(home);
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
    public void openTwitter(String link) {
        Uri uri = Uri.parse("https://twitter.com/" + link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void openFacebook(String link) {
        Uri uri = Uri.parse("https://www.facebook.com/" + link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void openYoutube(String link) {
        Uri uri = Uri.parse("https://www.youtube.com/user/" + link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void openEmail(String link) {
        try {
            Uri email = Uri.parse(link);
            Intent intent = new Intent(Intent.ACTION_VIEW, email);
            startActivity(intent);
        } catch (NullPointerException e) {
            Toast.makeText(this, "This Congressmember's contact form is unavailable at this time.",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void openWebsite(String link) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void openPhont(String link) {
        Uri uri = Uri.parse("tel: " + link);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }
}
