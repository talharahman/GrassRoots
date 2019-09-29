package org.grassrootsapp.grassroots.network.ProPublica.OfficeExpense;

import org.grassrootsapp.grassroots.utils.OfficeExpUIListener;
import org.grassrootsapp.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;

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
            }

            @Override
            public void failedCall() {
                officeExpUIListener.noResponseOfficeExpUI();
            }
        });

    }
}
