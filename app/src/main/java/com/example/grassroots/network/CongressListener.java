package com.example.grassroots.network;

import com.example.grassroots.model.CongressResponse;

public interface CongressListener {
    void successfulCall(CongressResponse congressResponse);

    void failedCall();

}
