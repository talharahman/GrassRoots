package org.grassrootsapp.grassroots.network.ProPublica.Bills;

import org.grassrootsapp.grassroots.model.ProPublica.Bills.BillSearchModel;

public interface BillInfoListener {

    void onSuccess(BillSearchModel billSearchModel);
    void onFailure();
}
