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
    private RepDirectoryFragmentListener repDirectoryFragmentListener;

    public RepresentativeDirectoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_representative_directory, container, false);
        createDirectory(requireContext().getString(R.string.Civic_Info_API_Key));
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RepDirectoryFragmentListener) {
            repDirectoryFragmentListener = (RepDirectoryFragmentListener) context;
        }
    }

    private void createDirectory(String string) {
        initialize();
        makeNetworkCall(string);
    }

    private void initialize() {
        presenter = new CivicInfoPresenter(new RepDirectoryFragmentListener() {
            @Override
            public void updateUI(CivicInfoModel civicInfoModel) {
                Log.d(MainActivity.TAG, "updateUI: " + civicInfoModel.getElectedRepresentatives().get(0));
                CivicInfoAdapter civicInfoAdapter = new CivicInfoAdapter();
                civicInfoAdapter.setadapterList(civicInfoModel.getElectedRepresentatives(), civicInfoModel.getPositions());
                recyclerView.setAdapter(civicInfoAdapter);
            }
        });
        recyclerView = rootView.findViewById(R.id.representative_recyclerviewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void makeNetworkCall(String string) {
        presenter.networkCall(string);
    }
}