package com.shortnr.url.services;

import com.google.common.hash.Hashing;
import com.shortnr.url.model.UrlObject;
import com.shortnr.url.model.UrlObjectRequest;
import com.shortnr.url.repository.UrlShortenerServiceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.shortnr.url.services.Base62Encoder.toBase64;

@Component
public class UrlShortenerService implements UrlShortenerServiceInterface {
    @Autowired
    private UrlShortenerServiceRepository urlDb;

    @Override
    public UrlObject generateShortUrl(UrlObjectRequest url) {
        if(StringUtils.isNotEmpty(url.getUrl())){
            UrlObject urlToSave = new UrlObject();

            String shortenedUrl = getShortUrlFrom(url.getUrl());
            urlToSave.setOriginalUrl(url.getUrl());
            urlToSave.setShortUrl(shortenedUrl);
            urlToSave.setUrlCreationDate(LocalDateTime.now());
            urlToSave.setUrlExpiryDate(getExpiryDate(url.getExpiryDate(), urlToSave.getUrlCreationDate()));

            return saveUrlToDb(urlToSave);
        }
        return null;
    }

    public String getShortUrlFrom(String origUrl){
        //get counter value and pass it to Base62 encode algorithm
        //return toBase64(1001001);
        String shortUrl = "";
        LocalDateTime timestamp = LocalDateTime.now();
        shortUrl = Hashing.sha256().hashString(origUrl.concat(timestamp.toString()), StandardCharsets.UTF_8).toString();
        return shortUrl;
    }

    public LocalDateTime getExpiryDate(String expiryDate, LocalDateTime creationDate){
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if(StringUtils.isBlank(expiryDate)){
            return creationDate.plusMinutes(5);
            //return creationDate.plusYears(1);
        }
        return  LocalDateTime.parse(expiryDate);
    }

    @Override
    public UrlObject saveUrlToDb(UrlObject urlToSave) {
        return urlDb.save(urlToSave);
    }

    @Override
    public UrlObject redirectShortUrl(String shortUrl) {
        return urlDb.findByShortUrl(shortUrl);
    }

}
