package com.example.grassroots.model.ProPublica.OfficeExpenses;

import java.util.List;

public class OfficeExpenseResponse {

    private String status;
    private String memberId;
    private String name;
    private Integer year;
    private Integer quarter;
    private Integer numResults;
    private List<OfficeExpResult> off_results;

    public OfficeExpenseResponse(List<OfficeExpResult> off_results){
        this.off_results = off_results;
    }

    public String getStatus() {
        return status;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public List<OfficeExpResult> getOff_results() {
        return off_results;
    }
}
