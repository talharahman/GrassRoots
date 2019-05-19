package com.example.grassroots.network.ProPublica.Members;


import com.example.grassroots.model.ProPublica.Members.CongressResponse;

public interface CongressListener {
    void successfulCall(CongressResponse congressResponse);

    void failedCall();

}
