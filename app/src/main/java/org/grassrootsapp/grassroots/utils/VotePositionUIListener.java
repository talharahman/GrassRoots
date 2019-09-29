package org.grassrootsapp.grassroots.utils;

import org.grassrootsapp.grassroots.model.ProPublica.VotePositions.VotePositionResponse;

public interface VotePositionUIListener {

    void updateUI(VotePositionResponse votePositionResponse);
}
