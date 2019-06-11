package com.example.grassroots.model.ProPublica.VotePositions;

public class Votes implements Comparable<Votes> {

   private Bill bill;
   private String description;
   private String result;
   private String date;
   private String position;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int compareTo(Votes o) {
        return this.getDescription().toLowerCase().compareTo(o.getDescription().toLowerCase());
    }
}
