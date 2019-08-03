package com.example.grassroots.model.ProPublica.OfficeExpenses;

import java.util.List;

public class OfficeExpenseResponse {

    private String status;
    private String member_id;
    private String name;
    private int year;
    private int quarter;
    private int num_results;
    private List<OfficeExpResult> results;

//    public OfficeExpenseResponse(List<OfficeExpResult> results){
//        this.results = results;
//    }

    public String getStatus() {
        return status;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getQuarter() {
        return quarter;
    }

    public int getNum_results() {
        return num_results;
    }

    public List<OfficeExpResult> getResults() {
        return results;
    }
}
