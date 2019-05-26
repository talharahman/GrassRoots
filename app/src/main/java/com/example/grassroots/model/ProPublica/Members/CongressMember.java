package com.example.grassroots.model.ProPublica.Members;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class CongressMember implements Comparable<CongressMember>, Parcelable {

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

    public CongressMember(){}

    protected CongressMember(Parcel in) {
        first_name = in.readString();
        last_name = in.readString();
        gender = in.readString();
        date_of_birth = in.readString();
        title = in.readString();
        short_title = in.readString();
        state = in.readString();
        party = in.readString();
        id = in.readString();
        fec_candidate_id = in.readString();
        next_election = in.readString();
        total_votes = in.readString();
        missed_votes = in.readString();
        twitter_account = in.readString();
        facebook_account = in.readString();
        youtube_account = in.readString();
        contact_form = in.readString();
        office = in.readString();
        phone = in.readString();
    }

    public static final Creator<CongressMember> CREATOR = new Creator<CongressMember>() {
        @Override
        public CongressMember createFromParcel(Parcel in) {
            return new CongressMember(in);
        }

        @Override
        public CongressMember[] newArray(int size) {
            return new CongressMember[size];
        }
    };

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

    public String getShort_title() {
        return short_title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(gender);
        dest.writeString(date_of_birth);
        dest.writeString(title);
        dest.writeString(short_title);
        dest.writeString(state);
        dest.writeString(party);
        dest.writeString(id);
        dest.writeString(fec_candidate_id);
        dest.writeString(next_election);
        dest.writeString(total_votes);
        dest.writeString(missed_votes);
        dest.writeString(twitter_account);
        dest.writeString(facebook_account);
        dest.writeString(youtube_account);
        dest.writeString(contact_form);
        dest.writeString(office);
        dest.writeString(phone);
    }
}
