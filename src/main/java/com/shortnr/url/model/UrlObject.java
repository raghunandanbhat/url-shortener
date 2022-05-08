package com.shortnr.url.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;


@Entity
public class UrlObject {
    @Id
    @GeneratedValue
    private Long uid;
    @Lob
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime urlCreationDate;
    private LocalDateTime urlExpiryDate;

    public UrlObject(){}

    public UrlObject(Long uid,
                     String originalUrl,
                     String shortUrl,
                     LocalDateTime urlCreationDate,
                     LocalDateTime urlExpiryDate){
        this.uid = uid;
        this.originalUrl = originalUrl;
        this. shortUrl= shortUrl;
        this.urlCreationDate = urlCreationDate;
        this.urlExpiryDate = urlExpiryDate;
    }

    /*
    * Getters and setters for class members
    * */
    public Long getUid(){
        return uid;
    }

    public void setUid(Long uid){
        this.uid = uid;
    }

    public String getOriginalUrl(){
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl){
        this.originalUrl = originalUrl;
    }

    public String getShortUrl(){
        return shortUrl;
    }

    public void setShortUrl(String shortUrl){
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getUrlCreationDate(){
        return urlCreationDate;
    }

    public void setUrlCreationDate(LocalDateTime creationDate){
        this.urlCreationDate = creationDate;
    }

    public LocalDateTime getUrlExpiryDate(){
        return urlExpiryDate;
    }

    public void setUrlExpiryDate(LocalDateTime expiryDate){
        this.urlExpiryDate = expiryDate;
    }

    public String urlToString(){
        return "UrlObject{" +
                "uid: " + uid +
                ", originalUrl: " + originalUrl +
                ", shortUrl: " + shortUrl +
                ", urlCreationDate: " + urlCreationDate +
                ", urlExpiryDate: " + urlExpiryDate + "}";
    }

}
