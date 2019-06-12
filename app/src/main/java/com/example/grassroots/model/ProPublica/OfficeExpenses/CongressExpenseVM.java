package com.example.grassroots.model.ProPublica.OfficeExpenses;

import android.arch.lifecycle.ViewModel;

import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpResult;

public class CongressExpenseVM extends ViewModel {

    private String category;
    private double amount;
    private double yearToDate;
    private double changeFromPreviousQuarter;
    private OfficeExpResult officeExpResult;

    public OfficeExpResult getOfficeExpResult() {
        return officeExpResult;
    }

    public void setOfficeExpResult(OfficeExpResult officeExpResult) {
        this.officeExpResult = officeExpResult;
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

    public double getYearToDate() {
        return yearToDate;
    }

    public void setYearToDate(double yearToDate) {
        this.yearToDate = yearToDate;
    }

    public double getChangeFromPreviousQuarter() {
        return changeFromPreviousQuarter;
    }

    public void setChangeFromPreviousQuarter(double changeFromPreviousQuarter) {
        this.changeFromPreviousQuarter = changeFromPreviousQuarter;
    }
}
