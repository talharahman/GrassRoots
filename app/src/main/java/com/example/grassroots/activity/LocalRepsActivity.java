package com.example.grassroots.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grassroots.R;
import com.example.grassroots.network.CivicInfo.CivicInfoPresenter;
import com.example.grassroots.recyclerview.CivicInfoAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocalRepsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private TextView userLocation;
    private RecyclerView recyclerView;
    private CivicInfoAdapter civicInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_reps);

        initialize();
    }

    private void initialize() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_contact);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_contact_home);
        floatingActionButton.setOnClickListener(v -> {
            Intent home = new Intent(getApplicationContext(), MainDashboard.class);
            startActivity(home);
        });

        SearchView searchView = findViewById(R.id.sv_local_reps);
        searchView.setOnQueryTextListener(this);

        userLocation = findViewById(R.id.user_district2);
        recyclerView = findViewById(R.id.local_reps_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        civicInfoAdapter = new CivicInfoAdapter();

        makeNetworkCall(this.getString(R.string.Civic_Info_API_Key), "11101");
    }

    private void makeNetworkCall(String key, String zipCode) {
        CivicInfoPresenter presenter = new CivicInfoPresenter(civicInfoModel -> {
            civicInfoAdapter.setAdapterList(civicInfoModel.getPositions(), civicInfoModel.getElectedRepresentatives());
            recyclerView.setAdapter(civicInfoAdapter);
            userLocation.setText(civicInfoModel.getNormalizedInput().getCity());
        });

        presenter.networkCall(key, zipCode);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bot_add_action:
                Intent action = new Intent(this, PetitionHostActivity.class);
                startActivity(action);
                return true;
            case R.id.bot_view_activity:
                Intent view = new Intent(this, UserViewActivity.class);
                startActivity(view);
                return true;
            case R.id.bot_nav_search:
                Intent search = new Intent(this, CongressActivity.class);
                startActivity(search);
                return true;
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        List<Address> searchAddresses;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            searchAddresses = geocoder.getFromLocationName(query, 1);
            if (searchAddresses.isEmpty()) {
                Toast.makeText(this,
                        "Invalid location", Toast.LENGTH_SHORT).show();
            }
            Address searchAddress = searchAddresses.get(0);
            String newZipCode = searchAddress.getPostalCode();
            makeNetworkCall(this.getString(R.string.Civic_Info_API_Key), newZipCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
