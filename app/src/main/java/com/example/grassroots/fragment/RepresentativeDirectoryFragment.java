package com.example.grassroots.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.CivicInfoAdapter;
import com.example.grassroots.MainActivity;
import com.example.grassroots.R;
import com.example.grassroots.model.CivicInfoModel;
import com.example.grassroots.network.CivicInfoPresenter;

public class RepresentativeDirectoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private View rootView;
    private CivicInfoPresenter presenter;

    public RepresentativeDirectoryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_representative_directory, container, false);
        createDirectory(getContext());
        return rootView;
    }

    private void createDirectory(Context context) {
        initialize();
        makeNetworkCall(context);
    }

    private void initialize() {
        presenter = new CivicInfoPresenter(this);
        recyclerView = rootView.findViewById(R.id.representative_recyclerviewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
    }

    private void makeNetworkCall(Context context) {
        presenter.networkCall(context);
    }

    public void updateUI(CivicInfoModel civicInfoModel) {
        Log.d(MainActivity.TAG, "updateUI: " + civicInfoModel.getElectedRepresentatives().get(6).getPhotoUrl());
        recyclerView.setAdapter(new CivicInfoAdapter(civicInfoModel.getElectedRepresentatives(), civicInfoModel.getPositions()));
    }
}