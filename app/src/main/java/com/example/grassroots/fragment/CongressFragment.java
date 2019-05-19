package com.example.grassroots.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.CongressAdapter;
import com.example.grassroots.R;
import com.example.grassroots.model.CongressMember;
import com.example.grassroots.model.CongressResponse;
import com.example.grassroots.network.CongressPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CongressFragment extends Fragment implements SearchView.OnQueryTextListener {

    public static final String TAG = "HERE";

    private CongressAdapter congressAdapter;
    private List<CongressMember> congressMembersList = new ArrayList<>();
    private CongressFragmentListener congressFragmentListener;
    private CongressPresenter congressPresenter;

    private RecyclerView recyclerView;

    public CongressFragment() {}

    public static CongressFragment newInstance(){
        return new CongressFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof CongressFragmentListener){
            congressFragmentListener = (CongressFragmentListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_congress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_congress);

        congressPresenter = new CongressPresenter(new CongressFragmentListener() {
            @Override
            public void updateCongressDirectoryUI(CongressResponse congressResponse) {
                congressAdapter = new CongressAdapter(congressMembersList);
                congressMembersList.addAll(congressResponse.getResults().get(0).getMembers());
                Log.d(TAG, "updateCongressDirectoryUI: "+ congressResponse.getResults().get(0).getMembers().get(0).getFirst_name());
                Collections.sort(congressMembersList);

                recyclerView.setAdapter(congressAdapter);
                congressAdapter.notifyDataSetChanged();
            }
        });

        congressPresenter.networkCall(requireContext().getString(R.string.ProPublica_Congress_API_Key));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        SearchView searchView = view.findViewById(R.id.sv_congress);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        final List<CongressMember> newMemberList = new ArrayList<>();
        for (CongressMember congressMember : congressMembersList) {
            if (congressMember.getFirst_name().toLowerCase().startsWith(s.toLowerCase())) {
                newMemberList.add(congressMember);
            }
        }
        congressAdapter.setData(newMemberList);
        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        congressFragmentListener = null;
    }
}
