package com.example.grassroots.network.ProPublica.Bills;

import com.example.grassroots.model.ProPublica.Bills.BillSearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface BillService {

    @GET("congress/v1/bills/search.json")
    Call<BillSearchModel> getBills(
            @Header("X-Api-Key") String ApiKey,
            @Query("query") String keywordSearch);
}
