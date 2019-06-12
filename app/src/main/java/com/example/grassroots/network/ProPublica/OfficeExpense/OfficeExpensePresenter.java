package com.example.grassroots.network.ProPublica.OfficeExpense;

import android.util.Log;

import com.example.grassroots.utils.OfficeExpUIListener;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;

public class OfficeExpensePresenter {

    private OfficeExpUIListener officeExpUIListener;


    public OfficeExpensePresenter(OfficeExpUIListener officeExpUIListener){
        this.officeExpUIListener = officeExpUIListener;
    }

    public void expenseNetworkCall(String congressApiKey, String member_id, int year, int quarter){

        ExpenseRepository expenseRepository = new ExpenseRepository();
        expenseRepository.fetchOfficeExpenses(congressApiKey, member_id, year, quarter, new OfficeExpenseListener() {
            @Override
            public void successfulCall(OfficeExpenseResponse officeExpenseResponse) {
                officeExpUIListener.updateOfficeExpUI(officeExpenseResponse);
                Log.d("OFFICEEXPENSEPRESENTER", "successfulCall: " + congressApiKey + " " + member_id);
            }

            @Override
            public void failedCall() {

            }
        });

    }
}
