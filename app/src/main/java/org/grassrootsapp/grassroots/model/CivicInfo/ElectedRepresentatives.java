package org.grassrootsapp.grassroots.model.CivicInfo;

import java.util.List;

public class ElectedRepresentatives {

    private String name;
    private String party;
    private List<String> phones;
    private List<String> urls;
    private String photoUrl;
    private List<SocialChannels> channels;
    private List<String> emails;

    private boolean isExpanded;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getName() {
        return name;
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
