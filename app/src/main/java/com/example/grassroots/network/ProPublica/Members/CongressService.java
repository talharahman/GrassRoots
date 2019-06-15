package com.example.grassroots.network.ProPublica.Members;

import com.example.grassroots.model.ProPublica.Members.CongressResponse;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;
import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CongressService {

    @GET("{congress}/{chamber}/members.json")
    Call<CongressResponse> getCongress(
            @Header("X-API-Key") String ApiKey,
            @Path("congress") String congress,
            @Path("chamber") String chamber
    );

//    @GET("members/{member-id}/votes.json")
//    Call<VotePositionResponse> getVotePositions(
//            @Header("X-API-Key") String ApiKey,
//            @Path("member-id") String memberId
//    );



    @GET("rzmorales/2f045e833a8283f7985f9107e7585ff9/raw/0922dd5ad58f92f6b8ab7f371f37bcd3699e9547/Bills%2520API%2520Endpoint%2520-%2520Temp")
    Call<VotePositionResponse> getVotePositions();

    @GET("members/{member-id}/office_expenses/{year}/{quarter}.json")
    Call<OfficeExpenseResponse> getOfficeExpenses(
            @Header("X-Api-Key") String apiKey,
            @Path("member-id") String member_id,
            @Path("year") int year,
            @Path("quarter") int quarter
    );

}
