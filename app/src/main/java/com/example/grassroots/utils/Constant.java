package com.example.grassroots.utils;

public enum Constant {
  CIVIC_INFO_BASE_URL("https://www.googleapis.com/"),
  CIVIC_INFO_END_POINT("civicinfo/v2/representatives");

    private final String urlPathReference;

    private Constant(String envUrl) {
        this.urlPathReference = envUrl;
    }

    public String getUrlPathReference() {
        return urlPathReference;
    }
}