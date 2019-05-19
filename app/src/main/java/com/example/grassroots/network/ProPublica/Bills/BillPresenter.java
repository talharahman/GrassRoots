package com.example.grassroots.network.ProPublica.Bills;

import android.util.Log;

import com.example.grassroots.fragment.BillsFragmentListener;
import com.example.grassroots.model.ProPublica.Bills.BillSearchModel;

public class BillPresenter {

    private BillsFragmentListener billsFragmentListener;

    public BillPresenter(BillsFragmentListener billsFragmentListener) {
        this.billsFragmentListener = billsFragmentListener;
    }

    public void networkCall(String proPublicaAPIKey) {
        BillRepository instance = new BillRepository();
        instance.fetchBills("megahertz", proPublicaAPIKey, new BillInfoListener() {
            @Override
            public void onSuccess(BillSearchModel billSearchModel) {
                billsFragmentListener.updateUI(billSearchModel);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
