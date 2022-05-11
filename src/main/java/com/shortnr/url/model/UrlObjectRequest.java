package com.shortnr.url.model;

public class UrlObjectRequest {
    private String url;
    private String expiryDate;

    public UrlObjectRequest(){}
    public UrlObjectRequest(String url, String expiryDate){
        this.url = url;
        this.expiryDate = expiryDate;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getExpiryDate() {
        return String.valueOf(expiryDate);
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String urlRequestToString(){
        return "UrlObjectRequest{" +
                "url: " + url +
                ", expiryDate: "+ expiryDate + "}";
    }
}

