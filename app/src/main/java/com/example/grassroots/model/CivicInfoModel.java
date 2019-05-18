package com.example.grassroots.model;

import java.util.List;

public class CivicInfoModel {

    private String kind;
    private CivicInfoUserInput normalizedInput;
    private List<ElectedPositions> offices;
    private List<ElectedRepresentatives> officials = null;

    public String getKind() {
        return kind;
    }

    public CivicInfoUserInput getNormalizedInput() {
        return normalizedInput;
    }

    public List<ElectedPositions> getPositions() {
        return offices;
    }

    public List<ElectedRepresentatives> getElectedRepresentatives() {
        return officials;
    }
}
