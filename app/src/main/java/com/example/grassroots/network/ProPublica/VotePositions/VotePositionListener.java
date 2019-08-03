package com.example.grassroots.network.ProPublica.VotePositions;

import com.example.grassroots.model.ProPublica.Bills.BillSearchModel;
import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResponse;

public interface VotePositionListener {

    void onSuccess(VotePositionResponse votePositionResponse);
    void onFailure();
}
