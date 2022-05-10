package com.shortnr.url.repository;

import com.shortnr.url.model.UrlObject;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerServiceRepository extends CassandraRepository<UrlObject, String> {
    public UrlObject findByShortUrl(String shortUrl);
}
