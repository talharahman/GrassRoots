package com.example.grassroots.model;

import java.util.List;

public class CongressResults {

    private String congress;
    private String chamber;
    private List<CongressMember> members;

    public String getCongress() {
        return congress;
    }

    public String getChamber() {
        return chamber;
    }

    public List<CongressMember> getMembers() {
        return members;
    }
}
