package com.demo.webfluxreactiveredis.repository;

import com.demo.webfluxreactiveredis.record.Link;
import reactor.core.publisher.Mono;

public interface LinkRepository {

    Mono<Link> save(Link link);

    Mono<Link> findByKey(String key);

}
