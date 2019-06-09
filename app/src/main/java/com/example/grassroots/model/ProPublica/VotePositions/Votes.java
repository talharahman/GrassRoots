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
        final int votes_description = this.getDescription().toLowerCase().compareTo(o.getDescription().toLowerCase());
        return votes_description;
    }
}

//    final int congressMember = this.getFirst_name().toLowerCase().compareTo(o.getFirst_name().toLowerCase());
//        return congressMember;