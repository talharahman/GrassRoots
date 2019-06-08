package com.example.grassroots.fragment.congress;


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

import com.example.grassroots.R;
import com.example.grassroots.fragment.OfficeExpUIListener;
import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;
import com.example.grassroots.network.ProPublica.OfficeExpense.OfficeExpensePresenter;
import com.example.grassroots.recyclerview.ExpenseAdapter;

public class OfficeExpFragment extends Fragment {

    private CongressOverviewVM congressOverviewVM;

//    private static final String KEY_YEAR = "Year" ;
//    private static final String KEY_QUARTER = "Quarter" ;

    private String member_id;
//    private String year;
//    private String quarter;

    public static final String TAG = "HERE";

    private ExpenseAdapter expenseAdapter;
    private RecyclerView recyclerView;

    public OfficeExpFragment() {
    }

//    public static OfficeExpFragment newInstance(int year, int quarter){
//        OfficeExpFragment officeExpFragment = new OfficeExpFragment();
//        Bundle officeArgs = new Bundle();
//        officeArgs.putInt(KEY_YEAR, year);
//        officeArgs.putInt(KEY_QUARTER, quarter);
//        officeExpFragment.setArguments(officeArgs);
//        return officeExpFragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(getArguments() != null){
//            quarter = (getArguments().getString(KEY_QUARTER)).substring(0,1);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_office_exp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        final Bundle officeArgs = getArguments();

        recyclerView = view.findViewById(R.id.rv_oe);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);
        member_id = congressOverviewVM.getCongressMember().getId();
//        int year = Integer.parseInt(officeArgs.getString(KEY_YEAR));
//        int quarterToInt = Integer.parseInt(quarter);

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

//        + officeArgs.getInt(KEY_YEAR) + " " + officeArgs.getInt(KEY_QUARTER)

    }
}
