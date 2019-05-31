package com.example.grassroots.network.ProPublica.OfficeExpense;

import android.util.Log;

import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;
import com.example.grassroots.network.ProPublica.Members.CongressService;
import com.example.grassroots.ui.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExpenseRepository {
    private static Retrofit instance;
    private static final String CONGRESS_BASE_URL = "https://api.propublica.org/congress/v1/";

    public ExpenseRepository() {}

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(CONGRESS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

//    public void fetchOfficeExpenses(String apiKey, String member_id, String year, String quarter, final OfficeExpenseListener officeExpenseListener){
//        getInstance()
//                .create(CongressService.class)
//                .getOfficeExpenses(apiKey, member_id, year, quarter)
//                .enqueue(new Callback<OfficeExpenseResponse>() {
//                    @Override
//                    public void onResponse(Call<OfficeExpenseResponse> call, Response<OfficeExpenseResponse> officeResponse) {
//                        OfficeExpenseResponse officeExpenseResponse = officeResponse.body();
//                        if(officeExpenseResponse != null){
//                            Log.d(MainActivity.TAG, "onResponse: " + officeExpenseResponse.getStatus());
//                            officeExpenseListener.successfulCall(officeExpenseResponse);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<OfficeExpenseResponse> call, Throwable t) {
//                        Log.d(MainActivity.TAG, "Call failed " + t.getMessage());
//                        officeExpenseListener.failedCall();
//                        //TO DO
//                    }
//                });
//    }
}
