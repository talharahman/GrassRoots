package org.grassrootsapp.grassroots.utils;

import org.grassrootsapp.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;

public interface OfficeExpUIListener {

    void updateOfficeExpUI(OfficeExpenseResponse officeExpenseResponse);
    void noResponseOfficeExpUI();

}
