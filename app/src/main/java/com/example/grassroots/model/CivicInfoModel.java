package com.example.grassroots.model;

import java.util.List;

public class CivicInfoModel {

    private String kind;
    private CivicInfoUserInput normalizedInput;
    private List<ElectedRepresentatives> officials = null;

    public String getKind() {
        return kind;
    }

    public CivicInfoUserInput getCivicInfoUserInput() {
        return normalizedInput;
    }

    public List<ElectedRepresentatives> getElectedRepresentatives() {
        return officials;
    }
}
