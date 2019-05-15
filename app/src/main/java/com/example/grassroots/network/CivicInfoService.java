package com.example.grassroots.network;

import com.example.grassroots.model.CivicInfoModel;
import com.example.grassroots.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CivicInfoService {

    @GET(Constant.KEYS.CIVIC_INFO_END_POINT)
    Call<CivicInfoModel> getCivicInfo(@Query("key") String key, @Query("address") String inputAddress);
}
