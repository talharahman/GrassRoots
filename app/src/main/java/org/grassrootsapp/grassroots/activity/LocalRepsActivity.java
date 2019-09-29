package org.grassrootsapp.grassroots.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.model.CivicInfo.CivicInfoModel;
import org.grassrootsapp.grassroots.model.petition.Petition;
import org.grassrootsapp.grassroots.model.user.UserActionViewModel;
import org.grassrootsapp.grassroots.network.CivicInfo.CivicInfoPresenter;
import org.grassrootsapp.grassroots.network.PetitionDB.FirebaseRepository;
import org.grassrootsapp.grassroots.recyclerview.CivicInfoAdapter;
import org.grassrootsapp.grassroots.utils.LocalRepsUIListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalRepsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private TextView userLocation;
    private RecyclerView recyclerView;
    private CivicInfoAdapter civicInfoAdapter;
    private UserActionViewModel userActionViewModel;
    private List<Petition> myPetitionsHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_reps);

        initialize();
    }

    private void initialize() {
        userActionViewModel = ViewModelProviders.of(this).get(UserActionViewModel.class);

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
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        civicInfoAdapter = new CivicInfoAdapter();

        makeNetworkCall(this.getString(R.string.Civic_Info_API_Key), "11101");
        makeDatabaseCall();
    }

    private void makeNetworkCall(String key, String zipCode) {
        CivicInfoPresenter presenter = new CivicInfoPresenter(new LocalRepsUIListener() {
            @Override
            public void updateUI(CivicInfoModel civicInfoModel) {
                civicInfoAdapter.setAdapterList(civicInfoModel.getPositions(), civicInfoModel.getElectedRepresentatives());
                recyclerView.setAdapter(civicInfoAdapter);
                userLocation.setText(civicInfoModel.getNormalizedInput().getCity());
            }
        });

        presenter.networkCall(key, zipCode);
    }

    private void makeDatabaseCall() {
        new FirebaseRepository().getAllPetitions(myPetitions -> {
            myPetitionsHistory = myPetitions;
            userActionViewModel.setPetitions(myPetitionsHistory);
        });

        civicInfoAdapter.setSendListener(representative -> {
            AlertDialog.Builder petitions = new AlertDialog.Builder(LocalRepsActivity.this);
            petitions.setIcon(R.drawable.send);
            petitions.setTitle("Choose a Petition to send");

            String[] myPetitionNames = {
                    myPetitionsHistory.get(myPetitionsHistory.size() -1).getmPetitionName(),
                    myPetitionsHistory.get(myPetitionsHistory.size() -2).getmPetitionName(),
                    myPetitionsHistory.get(0).getmPetitionName(),
                    myPetitionsHistory.get(1).getmPetitionName()};


            int checkedItem = 0;
            petitions.setSingleChoiceItems(myPetitionNames, checkedItem, (dialog, which) -> { });
            petitions.setPositiveButton("OK", (dialog, which) -> {
                Toast.makeText(LocalRepsActivity.this, "Petition sent to " + representative.getName(), Toast.LENGTH_SHORT).show();
            });
            petitions.setNegativeButton("Cancel", null);

            AlertDialog dialog = petitions.create();
            dialog.show();
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

            if (searchAddresses.isEmpty() ) {
                Toast.makeText(this, "Invalid location", Toast.LENGTH_SHORT).show();
            }

            Address searchAddress = searchAddresses.get(0);

            String newZipCode = searchAddress.getPostalCode();

            makeNetworkCall(this.getString(R.string.Civic_Info_API_Key), newZipCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
