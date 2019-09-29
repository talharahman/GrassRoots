package org.grassrootsapp.grassroots.network.ProPublica.OfficeExpense;

import org.grassrootsapp.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;

public interface OfficeExpenseListener {
    void successfulCall(OfficeExpenseResponse officeExpenseResponse);

    void failedCall();
}
