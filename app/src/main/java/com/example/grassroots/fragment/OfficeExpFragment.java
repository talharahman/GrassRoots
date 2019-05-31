package com.example.grassroots.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.CongressExpenseVM;
import com.example.grassroots.R;

public class OfficeExpFragment extends Fragment  {

    private CongressExpenseVM congressExpenseVM;
    private OfficeExpUIListener officeExpUIListener;

    private static final String KEY_MEMBER_ID = "Member ID" ;
    private static final String KEY_YEAR = "Year" ;
    private static final String KEY_QUARTER = "Quarter" ;

    private String member_id;
    private String year;
    private String quarter;

    public OfficeExpFragment() {
    }

    public static OfficeExpFragment newInstance(String member_id, String year, String quarter){
//        return new OfficeExpFragment();
        OfficeExpFragment officeExpFragment = new OfficeExpFragment();
        Bundle officeArgs = new Bundle();
        officeArgs.putString(KEY_MEMBER_ID, member_id);
        officeArgs.putString(KEY_YEAR, year);
        officeArgs.putString(KEY_QUARTER, quarter);
        officeExpFragment.setArguments(officeArgs);
        return officeExpFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OfficeExpUIListener){
            officeExpUIListener = (OfficeExpUIListener) context;
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_office_exp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle videoArgs = getArguments();
        congressExpenseVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressExpenseVM.class);

//
//        ExpensePresenter expensePresenter = new ExpensePresenter(officeExpenseResponse -> {
//        });
//
//        TextView amt_1 = view.findViewById(R.id.amt_1);
//        amt_1.setText(String.valueOf(congressExpenseVM.getOfficeExpResult().getAmount()));
    }
}
