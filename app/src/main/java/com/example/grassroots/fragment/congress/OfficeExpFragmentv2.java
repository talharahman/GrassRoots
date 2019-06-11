package com.example.grassroots.fragment.congress;


import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grassroots.R;
import com.example.grassroots.fragment.OfficeExpUIListener;
import com.example.grassroots.fragment.petition.PetitionFirstFragment;
import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;
import com.example.grassroots.network.ProPublica.OfficeExpense.OfficeExpensePresenter;
import com.example.grassroots.recyclerview.ExpenseAdapter;

import java.nio.file.Path;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;

public class OfficeExpFragmentv2 extends Fragment {

    private CongressOverviewVM congressOverviewVM;

    private String member_id;
    private String year;
    private String quarter;
    private String short_title;

    private int yr_;
    private int qt_;

    private Spinner oe_yr_spinner;
    private Spinner oe_qt_spinner;

    public static final String TAG = "HERE";

    private ExpenseAdapter expenseAdapter;
    private RecyclerView recyclerView;

    private OfficeExpensePresenter officeExpensePresenter;

    public OfficeExpFragmentv2() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_office_exp_design_v2, container, false);
        oe_yr_spinner = root.findViewById(R.id.oe_yr_spinner_off_exp);
        oe_qt_spinner = root.findViewById(R.id.oe_qt_spinner_off_exp);

        ArrayAdapter<CharSequence> oe_yr_adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.years, android.R.layout.simple_spinner_item);
        oe_yr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oe_yr_spinner.setAdapter(oe_yr_adapter);


        ArrayAdapter<CharSequence> oe_qt_adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.quarter, android.R.layout.simple_spinner_item);
        oe_qt_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oe_qt_spinner.setAdapter(oe_qt_adapter);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSpinners();

        ImageView btn_disclaimer_yr_qt = view.findViewById(R.id.btn_disclaimer_year_quarter);
        btn_disclaimer_yr_qt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(OfficeExpFragmentv2.this.requireContext()).create();
                alertDialog.setTitle("Note");
                alertDialog.setMessage(OfficeExpFragmentv2.this.getResources().getString(R.string.instruction_view_oe));
                alertDialog.show();
            }
        });

        recyclerView = view.findViewById(R.id.rv_office_expenses);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);
        member_id = congressOverviewVM.getCongressMember().getId();
        short_title = congressOverviewVM.getCongressMember().getShort_title();

        if (short_title.equals("Rep.")) {
            recyclerView.setVisibility(View.VISIBLE);
        } else if (short_title.equals("Sen.")) {
            insertCalltoActionFragment();
        }

        /* edge cases for office expense reports
        1. newly elected reps
        2. 2009, 1st-2nd qt unavailable
        3. senators
        */

        officeExpensePresenter = new OfficeExpensePresenter(new OfficeExpUIListener() {
            @Override
            public void updateOfficeExpUI(OfficeExpenseResponse officeExpenseResponse) {
                expenseAdapter = new ExpenseAdapter(officeExpenseResponse.getResults());
                recyclerView.setAdapter(expenseAdapter);
                expenseAdapter.notifyDataSetChanged();
            }
        });


        if (year == null && quarter == null) {
            Toast.makeText(getContext(), "Select year and quarter", Toast.LENGTH_SHORT).show();
        }
    }


    private void setSpinners() {
        oe_yr_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = parent.getItemAtPosition(position).toString();
                Log.d("SPINNEROEFRAG", "onItemSelected: " + year);
                if(!year.equals("") && !quarter.equals("")) {
                    if (year != null && quarter != null) {
                        if(year.equals("2009") && quarter.equals("1") || year.equals("2009") && quarter.equals("2") ){
                            insertInfoNotAvailable();
                        }
                        yr_ = Integer.parseInt(year);
                        qt_ = Integer.parseInt(quarter);
                        expenseCallback(yr_, qt_);
                    }

//                    while(year != null && quarter != null) {
//                        yr_ = Integer.parseInt(year);
//                        qt_ = Integer.parseInt(quarter);
//                        expenseCallback(yr_, qt_);
//                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        oe_qt_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quarter = parent.getItemAtPosition(position).toString();
                Log.d("SPINNEROEFRAG", "onItemSelected: " + quarter);
                if(!year.equals("") && !quarter.equals("")) {
                    if (year != null && quarter != null) {
                        if(year.equals("2009") && quarter.equals("1") || year.equals("2009") && quarter.equals("2")){
                            insertInfoNotAvailable();
                        }
                        yr_ = Integer.parseInt(year);
                        qt_ = Integer.parseInt(quarter);
                        expenseCallback(yr_, qt_);
                    }
//                    while(year != null && quarter != null) {
//                        yr_ = Integer.parseInt(year);
//                        qt_ = Integer.parseInt(quarter);
//                        expenseCallback(yr_, qt_);
//                    }
                }
//                if(!year.equals("") && !quarter.equals("")) {
//                    if(year != null && quarter != null) {
//                        yr_ = Integer.parseInt(year);
//                        qt_ = Integer.parseInt(quarter);
//                        expenseCallback(yr_, qt_);
//                    }
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void expenseCallback(int year, int quarter) {
        officeExpensePresenter.expenseNetworkCall(requireContext().getString(R.string.ProPublica_Congress_API_Key),
                member_id, year, quarter);
    }

    private void insertCalltoActionFragment() {
        TransparencyFragment transparencyFragment = new TransparencyFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_oe, transparencyFragment).addToBackStack(null).commit();
    }

    private void insertInfoNotAvailable() {
        InfoNotAvailableFragment infoNotAvailableFragment = new InfoNotAvailableFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_oe_rv, infoNotAvailableFragment).addToBackStack(null).commit();
        Log.d("INFONOTAVAILABLEFRAG", "insertInfoNotAvailableFragment: ");
    }
}
