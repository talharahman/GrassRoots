package com.example.grassroots.model.ProPublica.VotePositions;

import java.util.List;

public class VotePositionResults {

    private String member_id;
    private List<Votes> votes;

    public String getMember_id() {
        return member_id;
    }

    public List<Votes> getVotes() {
        return votes;
    }
}
