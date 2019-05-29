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

    @GET("members/{member-id}/votes.json")
    Call<VotePositionResponse> getVotePositions(
            @Header("X-API-Key") String ApiKey,
            @Path("member_id") String memberId
    );

    @GET("{member-id}/office_expenses/{year}/{quarter}.json")
    Call<OfficeExpenseResponse> getOfficeExpenses(
            @Header("X-API-Key") String ApiKey,
            @Path("member_id") String memberID,
            @Path("year") String year,
            @Path("quarter") String quarter
    );

}
