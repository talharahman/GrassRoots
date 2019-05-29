package com.example.grassroots.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.grassroots.CongressOverviewVM;
import com.example.grassroots.R;

public class SpinnerOEFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private CongressOverviewVM congressOverviewVM;
    private OfficeExpFragmentListener officeExpFragmentListener;
    private String member_id;

    public SpinnerOEFragment() {
     }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OfficeExpFragmentListener){
            officeExpFragmentListener = (OfficeExpFragmentListener) context;
        }
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);

        member_id = congressOverviewVM.getCongressMember().getId();

        Button btn_exp_submit = view.findViewById(R.id.btn_exp_submit);


        btn_exp_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                officeExpFragmentListener.toOfficeExpenseRecords(member_id);
                Log.d("FINDFINDFIND", "onClick: " + member_id);
            }
        });
    }

}
