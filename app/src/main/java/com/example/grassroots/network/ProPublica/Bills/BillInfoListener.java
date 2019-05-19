package com.example.grassroots.network.ProPublica.Bills;

import com.example.grassroots.model.ProPublica.Bills.BillSearchModel;

public interface BillInfoListener {

    void onSuccess(BillSearchModel billSearchModel);
    void onFailure();
}
