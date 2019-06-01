package com.example.grassroots.network.ProPublica.OfficeExpense;

import com.example.grassroots.CongressExpenseVM;
import com.example.grassroots.fragment.OfficeExpUIListener;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;

public class OfficeExpensePresenter {

    private OfficeExpUIListener officeExpUIListener;


    public OfficeExpensePresenter(OfficeExpUIListener officeExpUIListener){
        this.officeExpUIListener = officeExpUIListener;
    }

    public void expenseNetworkCall(String congressApiKey, String member_id){

        ExpenseRepository expenseRepository = new ExpenseRepository();
        expenseRepository.fetchOfficeExpenses(congressApiKey, member_id, 2017, 4, new OfficeExpenseListener() {
            @Override
            public void successfulCall(OfficeExpenseResponse officeExpenseResponse) {
                officeExpUIListener.updateOfficeExpUI(officeExpenseResponse);
            }

            @Override
            public void failedCall() {

            }
        });

    }
}
