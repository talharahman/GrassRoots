package com.example.grassroots.model.CivicInfo;

import java.util.List;

public class CivicInfoModel {

    private CivicInfoUserInput normalizedInput;
    private List<ElectedPositions> offices;
    private List<ElectedRepresentatives> officials;

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
