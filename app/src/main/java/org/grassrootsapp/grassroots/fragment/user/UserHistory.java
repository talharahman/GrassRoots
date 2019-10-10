package org.grassrootsapp.grassroots.fragment.user;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.grassrootsapp.grassroots.R;

import org.grassrootsapp.grassroots.model.petition.Petition;
import org.grassrootsapp.grassroots.model.user.UserActionViewModel;
import org.grassrootsapp.grassroots.recyclerview.PetitionActivityAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserHistory extends Fragment {

    RecyclerView recyclerViewUserHistory;
    PetitionActivityAdapter petitionActivityAdapter;
    List<Petition> mySignedPetitions;
    UserActionViewModel userActionViewModel;

    public UserHistory() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_history, container, false);
    }

    public static UserHistory newInstance() {
        return new UserHistory();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewUserHistory = view.findViewById(R.id.user_saved_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewUserHistory.setLayoutManager(layoutManager);

        userActionViewModel = ViewModelProviders.of(requireActivity()).get(UserActionViewModel.class);

        MutableLiveData<List<Petition>> petitionsHistory = userActionViewModel.getPetitionsSignedByUser();

        petitionsHistory.observe(this, new Observer<List<Petition>>() {
            @Override
            public void onChanged(@Nullable List<Petition> petitions) {
                petitionActivityAdapter = new PetitionActivityAdapter();
                mySignedPetitions = new ArrayList<>();
                mySignedPetitions.addAll(petitions);
                Collections.reverse(mySignedPetitions);

                petitionActivityAdapter.setPetitionActivityAdapterList(mySignedPetitions, userActionViewModel.getCurrentUserID(), 1);

                recyclerViewUserHistory.setAdapter(petitionActivityAdapter);
                petitionActivityAdapter.notifyDataSetChanged();

                // filter petitions that have my userId as signers
            }
        });

    }
}