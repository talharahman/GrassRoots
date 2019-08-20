package com.example.grassroots.network.ProPublica.VotePositions;

import android.util.Log;

import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResponse;
import com.example.grassroots.network.ProPublica.Members.CongressService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class VotePositionRepository {
    private static Retrofit instance;
    private static final String VOTE_POSITIONS_BASE_URL = "https://api.propublica.org/congress/v1/";
//    private static final String VOTE_POSITIONS_BASE_URL = "https://gist.githubusercontent.com/";

    VotePositionRepository() {}

    private static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(VOTE_POSITIONS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    void fetchVotePositions(String apiKey, final String member_id, final VotePositionListener votePositionListener) {
        getInstance()
                .create(CongressService.class)
                .getVotePositions(apiKey, member_id)
                .enqueue(new Callback<VotePositionResponse>() {
            @Override
            public void onResponse(Call<VotePositionResponse> call, Response<VotePositionResponse> response) {
                VotePositionResponse votePositionResponse = response.body();
                Log.d("HERE", "VP onResponse: " + response.code());
                Log.d("HERE", "VP onResponse: " + response.errorBody());

                Log.d("HERE", "VP onResponse: " + response.message());
                Log.d("HERE", "VP onResponse: " + response.raw());

                if (response.body() != null) {
                    votePositionListener.onSuccess(votePositionResponse);
                }
            }

            @Override
            public void onFailure(Call<VotePositionResponse> call, Throwable t) {
                Log.d("ONERROR", "onFailure: " + t.getMessage());
            }
        });
    }

}
