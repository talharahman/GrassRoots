package com.example.grassroots.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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

import com.example.grassroots.CongressExpenseVM;
import com.example.grassroots.CongressOverviewVM;
import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;
import com.example.grassroots.network.ProPublica.Members.CongressService;
import com.example.grassroots.network.ProPublica.OfficeExpense.ExpenseRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpinnerOEFragment extends Fragment {

    // implements OfficeExpUIListener

    private CongressOverviewVM congressOverviewVM;
    public static final String MEMBER_KEY = "Member Id";
    public static final String YEAR_KEY = "Year";
    public static final String QUARTER_KEY = "Quarter";

    private String member_id;
    private String year;
    private String quarter;

    private OfficeExpUIListener officeExpUIListener;

    private Spinner yr_spinner;
    private Spinner qt_spinner;

    public SpinnerOEFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OfficeExpUIListener) {
//            officeExpUIListener = (OfficeExpUIListener) context;
//        }
//    }

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


        Button btn_exp_submit = view.findViewById(R.id.btn_exp_submit);

        btn_exp_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                WORKING BUT SEE TWO LAYOUTS
                Log.d("FINDFINDFIND", "onClick: before inserting frag");
                insertOENestedFragment();
                Log.d("FINDFINDFIND", "onClick: frag inserted");

                //PROBLEMATIC:"java.lang.NullPointerException: Attempt to invoke virtual method 'void com.example.grassroots.recyclerview.CongressAdapter.setData(java.util.List)' on a null object reference
                //        at com.example.grassroots.ui.CongressActivity.onQueryTextChange(CongressActivity.java:89)"
//                if (officeExpUIListener != null) {
//                    officeExpUIListener.toOfficeExpenseFragment(member_id, year, quarter);
//                    Log.d("FINDFINDFIND", "onClick: CLICKED");
//                }
                //NOTWORKING
//                officeExpUIListener.toOfficeExpenseFragment(member_id, year, quarter);
//                Log.d("FINDFINDFIND", "onClick: " + member_id + " " + year + " " + quarter);
            }
        });
    }

    private void insertOENestedFragment() {
        OfficeExpFragment officeExpFragment = OfficeExpFragment.newInstance(member_id, year, quarter);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_spinner, officeExpFragment).commit();

        //hide but hides both
//        transaction.hide(this).replace(R.id.container_spinner, officeExpFragment).commit();
//    }

//    @Override
//    public void toOfficeExpenseFragment(String member_id, String year, String quarter) {
//        OfficeExpFragment officeExpFragment = new OfficeExpFragment();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.container_spinner, officeExpFragment).commit();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        officeExpUIListener = null;
//    }
    }
}

//        ExpenseRepository.getInstance()
//                .create(CongressService.class)
//                .getOfficeExpenses(requireContext().getString(R.string.ProPublica_Congress_API_Key), MEMBER_KEY, YEAR_KEY, QUARTER_KEY)
//                .enqueue(new Callback<OfficeExpenseResponse>() {
//                    @Override
//                    public void onResponse(Call<OfficeExpenseResponse> call, Response<OfficeExpenseResponse> response) {
//                        Log.d("FINDFINDFIND", "onResponse: " + response.body().getOff_results().get(0).getAmount());
//                    }
//
//                    @Override
//                    public void onFailure(Call<OfficeExpenseResponse> call, Throwable t) {
//
//                    }
//                });