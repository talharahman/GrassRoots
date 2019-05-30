package com.example.grassroots.network.ProPublica.VotePositions;

import com.example.grassroots.fragment.VotePositionUIListener;
import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResponse;

public class VotePostitionPresenter {
    private VotePositionUIListener positionUIListener;

    public VotePostitionPresenter(VotePositionUIListener votePositionUIListener){
        this.positionUIListener = votePositionUIListener;
    }

    public void networkCall(String congressApiKey){
        VotePositionRepository instance = new VotePositionRepository();
        instance.fetchVotePositions(congressApiKey, "K000388", new VotePositionListener() {
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
