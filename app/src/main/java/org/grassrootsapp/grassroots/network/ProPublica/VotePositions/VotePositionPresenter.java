package org.grassrootsapp.grassroots.network.ProPublica.VotePositions;

import org.grassrootsapp.grassroots.utils.VotePositionUIListener;
import org.grassrootsapp.grassroots.model.ProPublica.VotePositions.VotePositionResponse;

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
