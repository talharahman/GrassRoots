package com.example.grassroots.network.ProPublica.Bills;

import com.example.grassroots.utils.BillsUIListener;
import com.example.grassroots.model.ProPublica.Bills.BillSearchModel;

public class BillPresenter {

    private BillsUIListener billsUIListener;

    public BillPresenter(BillsUIListener billsUIListener) {
        this.billsUIListener = billsUIListener;
    }

    public void networkCall(String proPublicaAPIKey) {
        BillRepository instance = new BillRepository();
        instance.fetchBills("megahertz", proPublicaAPIKey, new BillInfoListener() {
            @Override
            public void onSuccess(BillSearchModel billSearchModel) {
                billsUIListener.updateUI(billSearchModel);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
