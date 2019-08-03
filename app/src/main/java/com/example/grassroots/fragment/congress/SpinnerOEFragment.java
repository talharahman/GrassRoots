package com.example.grassroots.fragment.congress;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;
import com.example.grassroots.R;

public class SpinnerOEFragment extends Fragment {

    private SpinnerOEFragment spinnerOEFragment;

    private CongressOverviewVM congressOverviewVM;

    private String member_id;
    private String year;
    private String quarter;

    private String short_title;

    private Spinner yr_spinner;
    private Spinner qt_spinner;


    public SpinnerOEFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.spinner_office_exp, container, false);
        yr_spinner = root.findViewById(R.id.yr_spinner_off_exp);
        qt_spinner = root.findViewById(R.id.qt_spinner_off_exp);

        ArrayAdapter<CharSequence> yr_adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.years, android.R.layout.simple_spinner_item);
        yr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yr_spinner.setAdapter(yr_adapter);


        ArrayAdapter<CharSequence> qt_adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.quarter, android.R.layout.simple_spinner_item);
        qt_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qt_spinner.setAdapter(qt_adapter);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        yr_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = parent.getItemAtPosition(position).toString();
                Log.d("SPINNEROEFRAG", "onItemSelected: " + year);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        qt_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quarter = parent.getItemAtPosition(position).toString();
                Log.d("SPINNEROEFRAG", "onItemSelected: " + quarter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);
        member_id = congressOverviewVM.getCongressMember().getId();
        short_title = congressOverviewVM.getCongressMember().getShort_title();


        Button btn_exp_submit = view.findViewById(R.id.btn_exp_submit);

        btn_exp_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_exp_submit.isPressed() && short_title.equals("Rep.")){
               //     insertOENestedFragment();
                    btn_exp_submit.setVisibility(v.GONE);
                } else if(btn_exp_submit.isPressed() && short_title.equals("Sen.")) {
                    insertCalltoActionFragment();
                    btn_exp_submit.setVisibility(v.INVISIBLE);
                }
            }
        });
    }

    private void insertInfoNotAvailableFragment() {
        InfoNotAvailableFragment infoNotAvailableFragment = new InfoNotAvailableFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_spinner, infoNotAvailableFragment).addToBackStack(null).commit();
        Log.d("INFONOTAVAILABLEFRAG", "insertInfoNotAvailableFragment: ");
    }

   /* private void insertOENestedFragment() {
        OfficeExpFragment officeExpFragment = new OfficeExpFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_spinner, officeExpFragment).addToBackStack(null).commit();
        Log.d("SPINNEROEFRAG", "insertOENestedFragment: inflate OE FRAG");

    }*/
    private void insertCalltoActionFragment() {
        TransparencyFragment transparencyFragment = new TransparencyFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_spinner, transparencyFragment).addToBackStack(null).commit();

    }


}
