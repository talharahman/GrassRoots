package org.grassrootsapp.grassroots.network.ProPublica.VotePositions;

import org.grassrootsapp.grassroots.model.ProPublica.VotePositions.VotePositionResponse;

public interface VotePositionListener {

    void onSuccess(VotePositionResponse votePositionResponse);
    void onFailure();
}
