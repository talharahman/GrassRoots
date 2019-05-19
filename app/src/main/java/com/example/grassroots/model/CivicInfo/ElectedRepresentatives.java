package com.example.grassroots.model.CivicInfo;

import java.util.List;

public class ElectedRepresentatives {

    private String name;
    private List<ElectedRepAddress> address = null;
    private String party;
    private List<String> phones = null;
    private List<String> urls = null;
    private String photoUrl;
    private List<SocialChannels> channels = null;
    private List<String> emails = null;

    public String getName() {
        return name;
    }

    public List<ElectedRepAddress> getAddress() {
        return address;
    }

    public String getParty() {
        return party;
    }

    public List<String> getPhones() {
        return phones;
    }

    public List<String> getUrls() {
        return urls;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public List<SocialChannels> getChannels() {
        return channels;
    }

    public List<String> getEmails() {
        return emails;
    }
}
