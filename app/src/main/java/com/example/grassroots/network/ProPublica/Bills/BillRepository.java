package com.example.grassroots.network.ProPublica.Bills;

import android.util.Log;

import com.example.grassroots.MainActivity;
import com.example.grassroots.model.ProPublica.Bills.BillSearchModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BillRepository {

    private static Retrofit retrofit;
    private static final String BILLS_BASE_URL = "https://api.propublica.org/";

    public BillRepository() {}

    static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BILLS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    void fetchBills(String billKeyword, String proPublicaKey, final BillInfoListener billInfoListener) {
        getInstance().create(BillService.class)
                .getBills(proPublicaKey, billKeyword)
                .enqueue(new Callback<BillSearchModel>() {
                    @Override
                    public void onResponse(Call<BillSearchModel> call, Response<BillSearchModel> response) {
                        BillSearchModel billSearchModel = response.body();
                        if (response.body() != null) {
                            billInfoListener.onSuccess(billSearchModel);
                        }
                    }

                    @Override
                    public void onFailure(Call<BillSearchModel> call, Throwable t) {

                    }
                });
    }
}
