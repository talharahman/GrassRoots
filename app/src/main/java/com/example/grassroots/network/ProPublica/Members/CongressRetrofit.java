package com.example.grassroots.network.ProPublica.Members;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CongressRetrofit {

    private static Retrofit instance;
    private static final String CONGRESS_BASE_URL = "https://api.propublica.org/congress/v1/";

    private CongressRetrofit(){}

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(CONGRESS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
