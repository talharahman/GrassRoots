package org.grassrootsapp.grassroots.model.ProPublica.VotePositions;

import java.util.List;

public class VotePositionResponse {

    private String status;
    private List<VotePositionResults> results;

    public VotePositionResponse(List<VotePositionResults> results){
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public List<VotePositionResults> getResults() {
        return results;
    }
}
