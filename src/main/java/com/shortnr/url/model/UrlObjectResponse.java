package com.shortnr.url.model;

import java.time.Instant;

public class UrlObjectResponse {
    private String url;
    private String shortUrl;
    private Instant expiryDate;

    public UrlObjectResponse(){}

    public UrlObjectResponse(String url, String shortUrl, Instant date){
        this.url = url;
        this.shortUrl = shortUrl;
        this.expiryDate = date;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getShortUrl(){
        return shortUrl;
    }

    public void setShortUrl(String shortUrl){
        this.shortUrl = shortUrl;
    }

    public Instant getExpiryDate(){
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String urlResponseToString(){
        return "UrlObjectResponse{" +
                "url: " + url +
                ", shortUrl: " + shortUrl +
                ", urlExpiryDate: " + expiryDate + "}";
    }
}
