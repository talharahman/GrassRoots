package org.grassrootsapp.grassroots.model.ProPublica.OfficeExpenses;

import java.io.Serializable;

public class OfficeExpResult implements Serializable {

    private String category;
    private double amount;
    private double year_to_date;
    private double change_from_previous_quarter;


    public OfficeExpResult(){}

    public OfficeExpResult(String category, double amount, double year_to_date, double change_from_previous_quarter){
        this.category = category;
        this.amount = amount;
        this.year_to_date = year_to_date;
        this.change_from_previous_quarter = change_from_previous_quarter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getYear_to_date() {
        return year_to_date;
    }

    public void setYear_to_date(double year_to_date) {
        this.year_to_date = year_to_date;
    }

    public double getChange_from_previous_quarter() {
        return change_from_previous_quarter;
    }

    public void setChange_from_previous_quarter(double change_from_previous_quarter) {
        this.change_from_previous_quarter = change_from_previous_quarter;
    }
}
