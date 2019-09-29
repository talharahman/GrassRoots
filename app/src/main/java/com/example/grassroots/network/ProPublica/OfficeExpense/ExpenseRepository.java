package com.example.grassroots.network.ProPublica.OfficeExpense;

import android.util.Log;
import android.widget.Toast;

import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;
import com.example.grassroots.network.ProPublica.Members.CongressService;

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

    public void fetchOfficeExpenses(String apiKey, final String member_id, int year, int quarter, final OfficeExpenseListener officeExpenseListener){
        getInstance()
                .create(CongressService.class)
                .getOfficeExpenses(apiKey, member_id, year, quarter)
                .enqueue(new Callback<OfficeExpenseResponse>() {
                    @Override
                    public void onResponse(Call<OfficeExpenseResponse> call, Response<OfficeExpenseResponse> response) {
                        OfficeExpenseResponse officeExpenseResponse = response.body();

                        Log.d("findme", "onResponse: " +response.raw());
                        Log.d("findme", "onResponse: " +response.message());
                        Log.d("findme", "onResponse: " +response.errorBody());
                        Log.d("findme", "onResponse: " +response.headers());
                        Log.d("findme", "onResponse: " +response.isSuccessful());
                        if(officeExpenseResponse != null){
                            officeExpenseListener.successfulCall(officeExpenseResponse);
                        } else {
                            officeExpenseListener.failedCall();
                        }
                    }

                    @Override
                    public void onFailure(Call<OfficeExpenseResponse> call, Throwable t) {
//                        officeExpenseListener.failedCall();
                        //TO DO
                    }
                });
    }
}
