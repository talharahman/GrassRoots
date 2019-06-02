package com.example.grassroots.fragment.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;

public class UserActions extends Fragment {

    public UserActions() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_feed, container, false);
    }

    public static UserActions newInstance() {
        return new UserActions();
    }

}
