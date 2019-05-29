package com.example.grassroots.network.ProPublica.Members;

import android.util.Log;

import com.example.grassroots.MainActivity;
import com.example.grassroots.model.ProPublica.Members.CongressResponse;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;
import com.example.grassroots.network.ProPublica.OfficeExpense.OfficeExpenseListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class CongressRepository {

    private static Retrofit instance;
    private static final String CONGRESS_BASE_URL = "https://api.propublica.org/congress/v1/";

    CongressRepository() {}

    private static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(CONGRESS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    void fetchHouseDirectory(String apiKey, final String congress, String chamber, final CongressListener congressListener) {
        getInstance()
                .create(CongressService.class)
                .getCongress(apiKey, congress, chamber)
                .enqueue(new Callback<CongressResponse>() {
                    @Override
                    public void onResponse(Call<CongressResponse> call, Response<CongressResponse> response) {
                        CongressResponse congressResponse = response.body();
                        if(congressResponse != null){
                            Log.d(MainActivity.TAG, "onResponse: " + congressResponse.getResults().get(0).getMembers().get(0).getFirst_name());
                            congressListener.successfulCall(congressResponse);
                        }
                   }

                    @Override
                    public void onFailure(Call<CongressResponse> call, Throwable t) {
                        Log.d(MainActivity.TAG, "Call failed " + t.getMessage());
                        congressListener.failedCall();
                        //TO DO

                    }
                });
    }

    void fetchSenateDirectory(String apiKey, String congress, String chamber, final CongressListener congressListener) {
        getInstance()
                .create(CongressService.class)
                .getCongress(apiKey, congress, chamber)
                .enqueue(new Callback<CongressResponse>() {
                    @Override
                    public void onResponse(Call<CongressResponse> call, Response<CongressResponse> response) {
                        CongressResponse congressResponse = response.body();
                        if(congressResponse != null){
                            Log.d(MainActivity.TAG, "onResponse: " + congressResponse.getResults().get(0).getMembers().get(0).getFirst_name());
                            congressListener.successfulCall(congressResponse);
                        }
                    }

                    @Override
                    public void onFailure(Call<CongressResponse> call, Throwable t) {
                        Log.d(MainActivity.TAG, "Call failed " + t.getMessage());
                        congressListener.failedCall();
                        //TO DO

                    }
                });
    }

    void fetchOfficeExpenses(String apiKey, String member_id, String year, String quarter, final OfficeExpenseListener officeExpenseListener){
        getInstance()
                .create(CongressService.class)
                .getOfficeExpenses(apiKey, member_id, year, quarter)
                .enqueue(new Callback<OfficeExpenseResponse>() {
                    @Override
                    public void onResponse(Call<OfficeExpenseResponse> call, Response<OfficeExpenseResponse> officeResponse) {
                        OfficeExpenseResponse officeExpenseResponse = officeResponse.body();
                        if(officeExpenseResponse != null){
                            Log.d(MainActivity.TAG, "onResponse: " + officeExpenseResponse.getStatus());
                            officeExpenseListener.successfulCall(officeExpenseResponse);
                        }
                    }

                    @Override
                    public void onFailure(Call<OfficeExpenseResponse> call, Throwable t) {
                        Log.d(MainActivity.TAG, "Call failed " + t.getMessage());
                        officeExpenseListener.failedCall();
                        //TO DO

                    }
                });
    }
}