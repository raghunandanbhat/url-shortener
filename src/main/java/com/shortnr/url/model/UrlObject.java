package com.shortnr.url.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import java.time.Instant;

@Table(value = "urls")
public class UrlObject {
    @Id @PrimaryKeyColumn(name = "short_url", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.TEXT)
    private String shortUrl;

    @Column("orig_url")
    @CassandraType(type = Name.TEXT)
    private String originalUrl;

    @Column("creation_date")
    @CassandraType(type = Name.TIMESTAMP)
    private Instant urlCreationDate;

    @Column("expiry_date")
    @CassandraType(type = Name.TIMESTAMP)
    private Instant urlExpiryDate;

    public UrlObject(){}

    public UrlObject(String shortUrl,
                     String originalUrl,
                     Instant urlCreationDate,
                     Instant urlExpiryDate){
        this.shortUrl= shortUrl;
        this.originalUrl = originalUrl;
        this.urlCreationDate = urlCreationDate;
        this.urlExpiryDate = urlExpiryDate;
    }

    /*
    * Getters and setters for class members
    * */

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

    public Instant getUrlCreationDate(){
        return urlCreationDate;
    }

    public void setUrlCreationDate(Instant creationDate){
        this.urlCreationDate = creationDate;
    }

    public Instant getUrlExpiryDate(){
        return urlExpiryDate;
    }

    public void setUrlExpiryDate(Instant expiryDate){
        this.urlExpiryDate = expiryDate;
    }

    public String urlToString(){
        return "UrlObject{" +
                "originalUrl: " + originalUrl +
                ", shortUrl: " + shortUrl +
                ", urlCreationDate: " + urlCreationDate +
                ", urlExpiryDate: " + urlExpiryDate + "}";
    }

}
