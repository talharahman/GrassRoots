package com.example.grassroots.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grassroots.recyclerview.CivicInfoAdapter;
import com.example.grassroots.R;
import com.example.grassroots.network.CivicInfo.CivicInfoPresenter;

public class LocalRepsFragment extends Fragment {

    private TextView userLocation;
    private RecyclerView recyclerView;
    private View rootView;
    private CivicInfoPresenter presenter;
    private CivicInfoAdapter civicInfoAdapter;

    private String zipCode;
    public static final String PARAM_KEY = "ZIP";

    public static LocalRepsFragment newInstance(String zipCode) {
        LocalRepsFragment repFragment = new LocalRepsFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_KEY, zipCode);
        repFragment.setArguments(args);
        return repFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            zipCode = getArguments().getString(PARAM_KEY);
        }
    }

    public LocalRepsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.local_reps_fragment_v2, container, false);
        createDirectory(requireContext().getString(R.string.Civic_Info_API_Key));
        return rootView;
    }

    private void createDirectory(String string) {
        initialize();
        makeNetworkCall(string, zipCode);
    }

    private void initialize() {
        userLocation = rootView.findViewById(R.id.user_district2);
        recyclerView = rootView.findViewById(R.id.local_reps_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
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
}