package com.example.grassroots.network.CivicInfo;

import android.util.Log;

import com.example.grassroots.model.CivicInfo.CivicInfoModel;
import com.example.grassroots.network.ProPublica.Members.CongressRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class CivicInfoRepository {

    private static Retrofit retrofit;
    private static final String CIVIC_INFO_BASE_URL = "https://www.googleapis.com/";

    CivicInfoRepository() {}

    static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(CIVIC_INFO_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    void fetchElectedRepresentatives(String streetAddress, String civicAPIKey, final CivicInfoListener civicInfoListener) {
        getInstance()
                .create(CivicInfoService.class)
                .getCivicInfo(civicAPIKey, streetAddress)
                .enqueue(new Callback<CivicInfoModel>() {
                    @Override
                    public void onResponse(Call<CivicInfoModel> call, Response<CivicInfoModel> response) {
                        CivicInfoModel civicInfoModel = response.body();
                        if (response.body() != null) {
                            Log.d("README", "onResponse: " + response.raw());
                            civicInfoListener.onSuccess(civicInfoModel);
                        }
                    }

                    @Override
                    public void onFailure(Call<CivicInfoModel> call, Throwable t) {
                        Log.d("README", "onFailure: " + t.getMessage());
                        civicInfoListener.onFailure();
                    }
                });
    }
}