package com.example.grassroots.utils;

import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;

public interface OfficeExpUIListener {

    void updateOfficeExpUI(OfficeExpenseResponse officeExpenseResponse);
    void noResponseOfficeExpUI();

}
