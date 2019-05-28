package com.example.grassroots.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.network.CivicInfo.CivicInfoPresenter;
import com.example.grassroots.recyclerview.CivicInfoAdapter;

public class LocalRepsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView userLocation;
    private RecyclerView recyclerView;
    private CivicInfoPresenter presenter;
    private CivicInfoAdapter civicInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_reps);
        createDirectory(this.getString(R.string.Civic_Info_API_Key));
    }

    private void createDirectory(String string) {
        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("ZIP");
        initialize();
        makeNetworkCall(string, zipCode);
    }

    private void initialize() {
        Toolbar toolbar = findViewById(R.id.toolbar_local_reps);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_contact);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        userLocation = findViewById(R.id.user_district2);
        recyclerView = findViewById(R.id.local_reps_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        civicInfoAdapter = new CivicInfoAdapter();
        presenter = new CivicInfoPresenter(civicInfoModel -> {
            civicInfoAdapter.setAdapterList(civicInfoModel.getPositions(), civicInfoModel.getElectedRepresentatives());
            recyclerView.setAdapter(civicInfoAdapter);
            userLocation.setText(civicInfoModel.getNormalizedInput().getCity());
        });
    }

    private void makeNetworkCall(String key, String zipCode) {
        presenter.networkCall(key, zipCode);
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
            case R.id.bot_nav_search:
                Intent searchIntent = new Intent(this, CongressActivity.class);
                startActivity(searchIntent);
                return true;
        }
        return true;
    }
}
