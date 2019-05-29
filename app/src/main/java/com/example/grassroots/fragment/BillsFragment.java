package com.example.grassroots.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.recyclerview.BillsAdapter;
import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Bills.BillSearchModel;
import com.example.grassroots.network.ProPublica.Bills.BillPresenter;


public class BillsFragment extends Fragment {

    private RecyclerView recyclerView;
    private View rootView;
    private BillPresenter presenter;

    public BillsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_bills, container, false);
        createDirectory(requireContext().getString(R.string.ProPublica_Congress_API_Key));
        return rootView;
    }

    private void createDirectory(String string) {
        initialize();
        makeNetworkCall(string);
    }

    private void initialize() {
        presenter = new BillPresenter(new BillsUIListener() {
            @Override
            public void updateUI(BillSearchModel billSearchModel) {
                BillsAdapter billsAdapter = new BillsAdapter();
                billsAdapter.setBillAdapterList(billSearchModel.getResults().get(0).getBills());
                recyclerView.setAdapter(billsAdapter);

            }
        });

        recyclerView = rootView.findViewById(R.id.bills_recyclerviewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void makeNetworkCall(String string) {
        presenter.networkCall(string);
    }
}

