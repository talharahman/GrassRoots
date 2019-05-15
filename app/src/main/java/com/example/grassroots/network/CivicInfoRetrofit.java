package com.example.grassroots.network;

import android.content.Context;
import android.util.Log;

import com.example.grassroots.MainActivity;
import com.example.grassroots.R;
import com.example.grassroots.model.CivicInfoModel;
import com.example.grassroots.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CivicInfoRetrofit {

    private static Retrofit retrofit;
    private static CivicInfoRetrofit thisInstance;
    private CivicInfoListener listener;

    private CivicInfoRetrofit() {}

    static CivicInfoRetrofit getInstance() {
        if (thisInstance == null) {
            thisInstance = new CivicInfoRetrofit();
        }
        return thisInstance;
    }

    private CivicInfoService onConnect() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.KEYS.CIVIC_INFO_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CivicInfoService.class);
    }

    void onSuccess(String inputAddress, Context context) {
        CivicInfoService civicInfoService = onConnect();
        civicInfoService
                .getCivicInfo(context.getString(R.string.Civic_Info_API_Key), inputAddress)
                .enqueue(new Callback<CivicInfoModel>() {
                    @Override
                    public void onResponse(Call<CivicInfoModel> call, Response<CivicInfoModel> response) {
                        CivicInfoModel civicInfoModel = response.body();
                        if (response.body() != null) {
                            Log.d(MainActivity.TAG, "onResponse: " + response.body().getElectedRepresentatives().get(0).getName());
                            listener.onConnected(civicInfoModel);
                        }
                    }

                    @Override
                    public void onFailure(Call<CivicInfoModel> call, Throwable t) {
                        Log.d(MainActivity.TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    void setListener(CivicInfoListener listener) {
        this.listener = listener;
    }
}