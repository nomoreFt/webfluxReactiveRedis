package com.demo.webfluxreactiveredis.service;

import com.demo.webfluxreactiveredis.record.Link;
import com.demo.webfluxreactiveredis.repository.LinkRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LinkService {

    private final String baseUrl;
    private final LinkRepository linkRepository;

    public LinkService(@Value("${app.baseUrl}") String baseUrl, LinkRepository linkRepository) {
        this.baseUrl = baseUrl;
        this.linkRepository = linkRepository;
    }

    public Mono<String> shortenLink(String link) {
        String randomKey = RandomStringUtils.randomAlphabetic(6);
        //save to database
        return linkRepository.save(new Link(link, randomKey))
                .map(result -> baseUrl + result.key());


    }

    public Mono<Link> getOriginalLink(String key) {
        return linkRepository.findByKey(key);
    }
}
