package com.shortnr.url.services;

import com.shortnr.url.model.UrlObject;
import com.shortnr.url.model.UrlObjectRequest;
import org.springframework.stereotype.Service;

@Service
public interface UrlShortenerServiceInterface {
    /*
    * Generates a unique short URL for the given long URL
    * and if a short URL is generated successfully, it will be
    * inserted to the database
    *
    * @param url- URL to be shortened and inserted to database
    * @return - URL object that includes long URL, shortened URL,
    *           creation date, expiry date, etc
    * */
    public UrlObject generateShortUrl(UrlObjectRequest url);

    /*
    * Saves the given URL object to database
    *
    * @param urlToSave - A url object that is to be inserted to database
    * @return UrlObject - A url object that has been successfully inserted to database.
    *                     NULL otherwise.
    * */
    public UrlObject saveUrlToDb(UrlObject urlToSave);

    public UrlObject redirectShortUrl(String shortUrl);
}
