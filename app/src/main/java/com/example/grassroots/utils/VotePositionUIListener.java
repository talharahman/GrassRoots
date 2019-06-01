package com.example.grassroots.utils;

import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResponse;

public interface VotePositionUIListener {

    void updateUI(VotePositionResponse votePositionResponse);
}
