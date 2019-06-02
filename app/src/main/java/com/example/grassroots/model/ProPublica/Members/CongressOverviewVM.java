package com.example.grassroots.model.ProPublica.Members;

import android.arch.lifecycle.ViewModel;

import com.example.grassroots.model.ProPublica.Members.CongressMember;

public class CongressOverviewVM extends ViewModel {

    private CongressMember congressMember;

    private String first_name;
    private String last_name;
    private String gender;
    private String date_of_birth;

    private String title;
    private String short_title;
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

    private String missed_votes_pct;
    private String votes_with_party_pct;

    public CongressMember getCongressMember() {
        return congressMember;
    }

    public void setCongressMember(CongressMember congressMember) {
        this.congressMember = congressMember;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFec_candidate_id() {
        return fec_candidate_id;
    }

    public void setFec_candidate_id(String fec_candidate_id) {
        this.fec_candidate_id = fec_candidate_id;
    }

    public String getNext_election() {
        return next_election;
    }

    public void setNext_election(String next_election) {
        this.next_election = next_election;
    }

    public String getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(String total_votes) {
        this.total_votes = total_votes;
    }

    public String getMissed_votes() {
        return missed_votes;
    }

    public void setMissed_votes(String missed_votes) {
        this.missed_votes = missed_votes;
    }

    public String getTwitter_account() {
        return twitter_account;
    }

    public void setTwitter_account(String twitter_account) {
        this.twitter_account = twitter_account;
    }

    public String getFacebook_account() {
        return facebook_account;
    }

    public void setFacebook_account(String facebook_account) {
        this.facebook_account = facebook_account;
    }

    public String getYoutube_account() {
        return youtube_account;
    }

    public void setYoutube_account(String youtube_account) {
        this.youtube_account = youtube_account;
    }

    public String getContact_form() {
        return contact_form;
    }

    public void setContact_form(String contact_form) {
        this.contact_form = contact_form;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMissed_votes_pct() {
        return missed_votes_pct;
    }

    public void setMissed_votes_pct(String missed_votes_pct) {
        this.missed_votes_pct = missed_votes_pct;
    }

    public String getVotes_with_party_pct() {
        return votes_with_party_pct;
    }

    public void setVotes_with_party_pct(String votes_with_party_pct) {
        this.votes_with_party_pct = votes_with_party_pct;
    }
}
