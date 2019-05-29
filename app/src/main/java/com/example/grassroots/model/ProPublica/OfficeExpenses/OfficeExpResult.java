package com.example.grassroots.model.ProPublica.OfficeExpenses;

public class OfficeExpResult {

    private String category;
    private double amount;
    private double yearToDate;
    private double changeFromPreviousQuarter;


    public OfficeExpResult(double amount, double yearToDate, double changeFromPreviousQuarter){
        this.amount = amount;
        this.yearToDate = yearToDate;
        this.changeFromPreviousQuarter = changeFromPreviousQuarter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getYearToDate() {
        return yearToDate;
    }

    public void setYearToDate(Double yearToDate) {
        this.yearToDate = yearToDate;
    }

    public double getChangeFromPreviousQuarter() {
        return changeFromPreviousQuarter;
    }

    public void setChangeFromPreviousQuarter(double changeFromPreviousQuarter) {
        this.changeFromPreviousQuarter = changeFromPreviousQuarter;
    }
}
