package com.shortnr.url.repository;

import com.shortnr.url.model.UrlObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlShortenerServiceRepository extends CrudRepository<UrlObject, Long> {
    public UrlObject findByShortUrl(String shortUrl);
}
