package com.example.grassroots.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Members.CongressMember;
import com.example.grassroots.network.ProPublica.Members.CongressPresenter;
import com.example.grassroots.recyclerview.CongressAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CongressActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private CongressAdapter congressAdapter;
    private List<CongressMember> congressMembersList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congress);

        initialize();
    }

    private void initialize() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_search_home);
        floatingActionButton.setOnClickListener(v -> {
            Intent home = new Intent(getApplicationContext(), MainDashboard.class);
            startActivity(home);
        });

        recyclerView = findViewById(R.id.rv_congress);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        SearchView searchView = findViewById(R.id.sv_congress);
        searchView.setOnQueryTextListener(this);

        networkCall();
    }

    private void networkCall() {
        CongressPresenter congressPresenter = new CongressPresenter(congressResponse -> {
            congressAdapter = new CongressAdapter(congressMembersList);
            congressMembersList.addAll(congressResponse.getResults().get(0).getMembers());
            Log.d("HERE", "updateCongressDirectoryUI: " + congressResponse.getResults().get(0).getMembers().get(0).getFirst_name());
            Collections.sort(congressMembersList);

            recyclerView.setAdapter(congressAdapter);
            congressAdapter.notifyDataSetChanged();
        });
        congressPresenter.networkCall(getApplicationContext().getString(R.string.ProPublica_Congress_API_Key));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<CongressMember> newMemberList = new ArrayList<>();
        for (CongressMember congressMember : congressMembersList) {
            if (congressMember.getFirst_name().toLowerCase().startsWith(newText.toLowerCase())) {
                newMemberList.add(congressMember);
            }
        }
        congressAdapter.setData(newMemberList);
        return false;
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
            case R.id.bot_nav_contact:
                Intent contact = new Intent(this, LocalRepsActivity.class);
                startActivity(contact);
                return true;
        }
        return true;
    }
}