package org.grassrootsapp.grassroots.fragment.congress;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.grassrootsapp.grassroots.R;

public class TransparencyFragment extends Fragment {


    public TransparencyFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transparency, container, false);
    }

}
