package com.shortnr.url.services;


import com.shortnr.url.model.UrlObject;
import com.shortnr.url.model.UrlObjectRequest;
import com.shortnr.url.repository.UrlShortenerServiceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeParseException;

import static com.shortnr.url.services.Base62Encoder.toBase64;

@Component
public class UrlShortenerService implements UrlShortenerServiceInterface {
    @Autowired
    private UrlShortenerServiceRepository urlDb;

    @Autowired
    private ZooKeeperCounterService zooKeeperCounterService;

    @Override
    public UrlObject generateShortUrl(UrlObjectRequest url) {
        if(StringUtils.isNotEmpty(url.getUrl())){
            UrlObject urlToSave = new UrlObject();

            String shortenedUrl = getShortUrlFrom(url.getUrl());

            urlToSave.setOriginalUrl(url.getUrl());
            urlToSave.setShortUrl(shortenedUrl);
            urlToSave.setUrlCreationDate(Instant.now());

            try{
                urlToSave.setUrlExpiryDate(Instant.parse(url.getExpiryDate()));
            }catch (DateTimeParseException e){
                System.out.println("expiry date is null");
                urlToSave.setUrlExpiryDate(urlToSave.getUrlCreationDate().plus(Duration.ofHours(24)));
            }
            System.out.println("Url generated");

            return saveUrlToDb(urlToSave);
        }
        return null;
    }

    public synchronized String getShortUrlFrom(String origUrl){
        //get counter value and pass it to Base62 encode algorithm
        long counter = zooKeeperCounterService.getRange();

        System.out.println("Counter value form zk server:" + counter);
        return toBase64(counter);
        /*
        String shortUrl = "";
        LocalDateTime timestamp = LocalDateTime.now();
        shortUrl = Hashing.sha256().hashString(origUrl.concat(timestamp.toString()), StandardCharsets.UTF_8).toString();
        return shortUrl;
        */
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
