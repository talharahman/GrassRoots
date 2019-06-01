package com.example.grassroots.fragment.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;

public class UserFeed extends Fragment {

    public UserFeed() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_actions, container, false);
    }

    public static UserFeed newInstance() {
        return new UserFeed();
    }

}
