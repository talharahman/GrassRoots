package com.example.grassroots.network.ProPublica.OfficeExpense;

import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;

public interface OfficeExpenseListener {
    void successfulCall(OfficeExpenseResponse officeExpenseResponse);

    void failedCall();
}
