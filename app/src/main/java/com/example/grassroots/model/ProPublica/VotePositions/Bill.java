package com.example.grassroots.model.ProPublica.VotePositions;

public class Bill {

    private String bill_id;
    private String title;
    private String latest_action;

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLatest_action() {
        return latest_action;
    }

    public void setLatest_action(String latest_action) {
        this.latest_action = latest_action;
    }
}
