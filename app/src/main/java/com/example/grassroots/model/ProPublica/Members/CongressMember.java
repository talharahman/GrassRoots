package com.example.grassroots.model.ProPublica.Members;

public class CongressMember implements Comparable<CongressMember>{

    private String first_name;
    private String last_name;
    private String gender;
    private String date_of_birth;

    private String title;
    private String state;
    private String party;
    private String id;
    private String fec_candidate_id;

    private String next_election;
    private String total_votes;
    private String missed_votes;

    private String twitter_account;
    private String facebook_account;
    private String youtube_account;

    private String contact_form;
    private String office;
    private String phone;

    public String getLast_name() {
        return last_name;
    }

    public String getParty() {
        return party;
    }

    public String getContact_form() {
        return contact_form;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getGender() {
        return gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public String getFec_candidate_id() {
        return fec_candidate_id;
    }

    public String getNext_election() {
        return next_election;
    }

    public String getTotal_votes() {
        return total_votes;
    }

    public String getMissed_votes() {
        return missed_votes;
    }

    public String getTwitter_account() {
        return twitter_account;
    }

    public String getFacebook_account() {
        return facebook_account;
    }

    public String getYoutube_account() {
        return youtube_account;
    }

    public String getOffice() {
        return office;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(CongressMember o) {
        final int congressMember = this.getFirst_name().toLowerCase().compareTo(o.getFirst_name().toLowerCase());
        return congressMember;
    }
}
