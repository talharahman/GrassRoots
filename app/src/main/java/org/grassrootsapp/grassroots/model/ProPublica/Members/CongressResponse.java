package org.grassrootsapp.grassroots.model.ProPublica.Members;

import java.util.List;

public class CongressResponse {

    private String status;
    private List<CongressResults> results;

    public CongressResponse(List<CongressResults> results){
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public List<CongressResults> getResults() {
        return results;
    }
}
