package com.example.grassroots.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;

public class VotePositionFragment extends Fragment {


    public VotePositionFragment() {
    }

    public static VotePositionFragment newInstance(){
        return new VotePositionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vote_position, container, false);
    }

}
