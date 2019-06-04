package com.example.grassroots.fragment.user;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.user.UserActionViewModel;

import java.util.List;

public class UserFeed extends Fragment {

    public UserFeed() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_history, container, false);
    }

    public static UserFeed newInstance() {
        return new UserFeed();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserActionViewModel userActionViewModel = ViewModelProviders.of(requireActivity()).get(UserActionViewModel.class);
        MutableLiveData<List<Petition>> myPetitions = userActionViewModel.getPetitions();
        myPetitions.observe(this, new Observer<List<Petition>>() {
            @Override
            public void onChanged(@Nullable List<Petition> petitions) {
                // filter petitions that have me as ownerId
            }
        });
    }

}
