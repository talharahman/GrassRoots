package com.example.grassroots.network.ProPublica.Members;

import com.example.grassroots.model.ProPublica.Members.CongressResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CongressRepository {

    public static Retrofit instance;
    private static final String CONGRESS_BASE_URL = "https://api.propublica.org/congress/v1/";

    public CongressRepository() {
    }

    public static Retrofit getInstance() {
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
                        if (congressResponse != null) {
                            congressListener.successfulCall(congressResponse);
                        }
                    }

                    @Override
                    public void onFailure(Call<CongressResponse> call, Throwable t) {
                        congressListener.failedCall();

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
                        if (congressResponse != null) {
                            congressListener.successfulCall(congressResponse);
                        }
                    }

                    @Override
                    public void onFailure(Call<CongressResponse> call, Throwable t) {
                        congressListener.failedCall();
                        //TO DO

                    }
                });
    }

}
