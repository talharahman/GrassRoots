package com.example.grassroots.fragment;

import com.example.grassroots.model.CivicInfo.CivicInfoModel;
import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResponse;

public interface VotePositionUIListener {

    void updateUI(VotePositionResponse votePositionResponse);
}
