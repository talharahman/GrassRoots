package com.example.grassroots.fragment.congress;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.fragment.OfficeExpUIListener;
import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;
import com.example.grassroots.network.ProPublica.OfficeExpense.OfficeExpensePresenter;
import com.example.grassroots.recyclerview.ExpenseAdapter;

public class OfficeExpFragmentv2 extends Fragment {

    private CongressOverviewVM congressOverviewVM;

    private String member_id;
    private String year;
    private String quarter;

    private Spinner oe_yr_spinner;
    private Spinner oe_qt_spinner;

    public static final String TAG = "HERE";

    private ExpenseAdapter expenseAdapter;
    private RecyclerView recyclerView;

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

        oe_yr_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = parent.getItemAtPosition(position).toString();
                Log.d("SPINNEROEFRAG", "onItemSelected: " + year);

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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        recyclerView = view.findViewById(R.id.rv_office_expenses);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);
        member_id = congressOverviewVM.getCongressMember().getId();

        TextView txt_title_name = view.findViewById(R.id.oe_txt_title_name);
        txt_title_name.setText(congressOverviewVM.getCongressMember().getShort_title() + " "
                + congressOverviewVM.getCongressMember().getFirst_name() + " "
                + congressOverviewVM.getCongressMember().getLast_name());

        OfficeExpensePresenter expensePresenter = new OfficeExpensePresenter(new OfficeExpUIListener() {
            @Override
            public void updateOfficeExpUI(OfficeExpenseResponse officeExpenseResponse) {
                expenseAdapter = new ExpenseAdapter(officeExpenseResponse.getResults());
                recyclerView.setAdapter(expenseAdapter);
                expenseAdapter.notifyDataSetChanged();
            }
        });

        expensePresenter.expenseNetworkCall(requireContext().getString(R.string.ProPublica_Congress_API_Key),
                member_id);
        Log.d(TAG, "ARGS FOR NETWORK CALL: " + member_id + " " );


    }
}
