package com.example.grassroots.fragment.user;


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

import com.example.grassroots.R;
import com.example.grassroots.activity.UserActionActivity;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.user.UserActionViewModel;
import com.example.grassroots.recyclerview.PetitionActivityAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserHistory extends Fragment {

    RecyclerView recyclerView;
    PetitionActivityAdapter petitionActivityAdapter;

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
        recyclerView= view.findViewById(R.id.user_saved_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);


        UserActionViewModel userActionViewModel = ViewModelProviders.of(requireActivity()).get(UserActionViewModel.class);
        MutableLiveData<List<Petition>> petitionsHistory = userActionViewModel.getPetitions();
        petitionsHistory.observe(this, new Observer<List<Petition>>() {
            @Override
            public void onChanged(@Nullable List<Petition> petitions) {
                petitionActivityAdapter = new PetitionActivityAdapter();
                List<Petition>  totalPetitions = new ArrayList<>();
                totalPetitions.addAll(petitions);
                totalPetitions.addAll(petitions);
                Log.d("ISTHISWORKING?", "onChanged: " + totalPetitions.size());

                petitionActivityAdapter.setPetitionActivityAdapterList(totalPetitions, petitions);


                recyclerView.setAdapter(petitionActivityAdapter);
                petitionActivityAdapter.notifyDataSetChanged();

                // filter petitions that have my userId as signers
            }
        });
        // 'this' is bc as a Lifecycle component, for every observer lmk what state they are in

    }
}
