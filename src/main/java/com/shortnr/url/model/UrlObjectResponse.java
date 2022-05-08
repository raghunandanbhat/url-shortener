package com.shortnr.url.model;

import java.time.LocalDateTime;

public class UrlObjectResponse {
    private String url;
    private String shortUrl;
    private LocalDateTime expiryDate;

    public UrlObjectResponse(){}

    public UrlObjectResponse(String url, String shortUrl, LocalDateTime date){
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

    public LocalDateTime getExpiryDate(){
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String urlResponseToString(){
        return "UrlObjectResponse{" +
                "url: " + url +
                ", shortUrl: " + shortUrl +
                ", urlExpiryDate: " + expiryDate + "}";
    }
}
