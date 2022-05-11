package com.shortnr.url.controller;

import com.shortnr.url.model.UrlObject;
import com.shortnr.url.model.UrlObjectErrorResponse;
import com.shortnr.url.model.UrlObjectRequest;
import com.shortnr.url.model.UrlObjectResponse;
import com.shortnr.url.services.UrlShortenerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1")
public class UrlShortenerServiceController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/generateShortURL")
    public ResponseEntity<?> generateShortUrl(@RequestBody UrlObjectRequest urlObject){
        UrlObject url = urlShortenerService.generateShortUrl(urlObject);

        if(url != null){
            UrlObjectResponse urlResponse = new UrlObjectResponse();

            urlResponse.setUrl(url.getOriginalUrl());
            urlResponse.setShortUrl(url.getShortUrl());
            urlResponse.setExpiryDate(url.getUrlExpiryDate());

            return new ResponseEntity<>(urlResponse, HttpStatus.OK);
        }

        UrlObjectErrorResponse urlErrorResponse = new UrlObjectErrorResponse();

        urlErrorResponse.setError("Failed to generate Short URL, try again!!!");
        urlErrorResponse.setStatus("404");

        return new ResponseEntity<>(urlErrorResponse, HttpStatus.OK);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirectShortUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException{

        if(StringUtils.isEmpty(shortUrl)){
            UrlObjectErrorResponse urlErrorResponse = new UrlObjectErrorResponse();

            urlErrorResponse.setError("Invalid short URL!");
            urlErrorResponse.setStatus("400");

            return new ResponseEntity<>(urlErrorResponse, HttpStatus.OK);
        }

        UrlObject redirectedUrl = urlShortenerService.redirectShortUrl(shortUrl);

        if( redirectedUrl == null){
            UrlObjectErrorResponse urlErrorResponse = new UrlObjectErrorResponse();

            urlErrorResponse.setError("Short URL does not exist or it is expired!");
            urlErrorResponse.setStatus("400");

            return new ResponseEntity<>(urlErrorResponse, HttpStatus.OK);
        }

        if(redirectedUrl.getUrlExpiryDate().isBefore(Instant.now())){
            //You may delete url
            UrlObjectErrorResponse urlErrorResponse = new UrlObjectErrorResponse();

            urlErrorResponse.setError("Short URL expired!");
            urlErrorResponse.setStatus("200");

            return new ResponseEntity<>(urlErrorResponse, HttpStatus.OK);
        }

        response.sendRedirect(redirectedUrl.getOriginalUrl());
        return null;
    }
}
