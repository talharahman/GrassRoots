package com.example.grassroots.network.ProPublica.Members;

import com.example.grassroots.model.ProPublica.Members.CongressResponse;

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
}
