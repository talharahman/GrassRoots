package com.example.grassroots.network.ProPublica.VotePositions;

import com.example.grassroots.utils.VotePositionUIListener;
import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResponse;

public class VotePositionPresenter {
    private VotePositionUIListener positionUIListener;

    public VotePositionPresenter(VotePositionUIListener votePositionUIListener){
        this.positionUIListener = votePositionUIListener;
    }

    public void networkCall(String congressApiKey, String member_id){
        VotePositionRepository instance = new VotePositionRepository();
        instance.fetchVotePositions(congressApiKey, member_id, new VotePositionListener() {
            @Override
            public void onSuccess(VotePositionResponse votePositionResponse) {
                positionUIListener.updateUI(votePositionResponse);
            }

            @Override
            public void onFailure() {

            }
        });

    }
}
