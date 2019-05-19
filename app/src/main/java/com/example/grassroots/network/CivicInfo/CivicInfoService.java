package com.example.grassroots.network.CivicInfo;

import com.example.grassroots.model.CivicInfo.CivicInfoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CivicInfoService {
    final String CIVIC_INFO_END_POINT = "civicinfo/v2/representatives";

    @GET(CIVIC_INFO_END_POINT)
    Call<CivicInfoModel> getCivicInfo(@Query("key") String key, @Query("address") String inputAddress);
}
