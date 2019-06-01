package com.example.grassroots.fragment;


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

import com.example.grassroots.CongressOverviewVM;
import com.example.grassroots.R;

public class SpinnerOEFragment extends Fragment {

    private SpinnerOEFragment spinnerOEFragment;

    private CongressOverviewVM congressOverviewVM;
    public static final String MEMBER_KEY = "Member Id";
    public static final String YEAR_KEY = "Year";
    public static final String QUARTER_KEY = "Quarter";

    private String member_id;
    private String year;
    private String quarter;

    private String short_title;

//    private SpinnerUIListener spinnerUIListener;

    private Spinner yr_spinner;
    private Spinner qt_spinner;

    public SpinnerOEFragment() {
    }
//
//    public static SpinnerOEFragment newInstance(String member_id, String year, String quarter){
//        SpinnerOEFragment spinnerOEFragment = new SpinnerOEFragment();
//        Bundle spinnerArgs = new Bundle();
//        spinnerArgs.putString(MEMBER_KEY, member_id);
//        spinnerArgs.putString(YEAR_KEY, year);
//        spinnerArgs.putString(QUARTER_KEY, quarter);
//        spinnerOEFragment.setArguments(spinnerArgs);
//        return spinnerOEFragment;
//    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof SpinnerUIListener) {
//            spinnerUIListener = (SpinnerUIListener) context;
//        }
//    }

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
                Log.d("FINDFINDFINd", "onItemSelected: " + year);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        qt_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quarter = parent.getItemAtPosition(position).toString();
                Log.d("FINDFINDFINd", "onItemSelected: " + quarter);

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
                Log.d("FINDFINDFIND", "onClick: before inserting frag");

                if(btn_exp_submit.isPressed() && short_title.equals("Rep.")){
                    insertOENestedFragment();
                    btn_exp_submit.setVisibility(View.INVISIBLE);
                } else if(btn_exp_submit.isPressed() && short_title.equals("Sen.")){
                    insertCalltoActionFragment();
                    btn_exp_submit.setVisibility(View.INVISIBLE);
                }

//                if(short_title.equals("Rep.")){
//                    insertOENestedFragment();
//                } else {
//                    insertCalltoActionFragment();
//                }
//                Log.d("FINDFINDFIND", "onClick: frag inserted");

                Log.d("FINDFINDFIND", "onClick: " + member_id + " " + year + " " + quarter);
            }
        });
    }

    private void insertCalltoActionFragment() {
        TransparencyFragment transparencyFragment = new TransparencyFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_spinner, transparencyFragment).addToBackStack(null).commit();
    }

    private void insertOENestedFragment() {
        OfficeExpFragment officeExpFragment = OfficeExpFragment.newInstance(member_id, year, quarter);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_spinner, officeExpFragment).addToBackStack(null).commit();


    }
}
