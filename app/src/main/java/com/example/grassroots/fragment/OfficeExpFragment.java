package com.example.grassroots.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.grassroots.R;

public class OfficeExpFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    public OfficeExpFragment() {
    }

    public static OfficeExpFragment newInstance(){
        return new OfficeExpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.spinner_office_exp, container, false);
        Spinner yr_spinner = root.findViewById(R.id.yr_spinner_off_exp);
        Spinner qt_spinner = root.findViewById(R.id.qt_spinner_off_exp);

        ArrayAdapter<CharSequence> yr_adapter =  ArrayAdapter.createFromResource(requireContext(),
                R.array.years, android.R.layout.simple_spinner_item);
        yr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yr_spinner.setAdapter(yr_adapter);
        yr_spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> qt_adapter =  ArrayAdapter.createFromResource(requireContext(),
                R.array.quarter, android.R.layout.simple_spinner_item);
        qt_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qt_spinner.setAdapter(qt_adapter);
        qt_spinner.setOnItemSelectedListener(this);

        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
