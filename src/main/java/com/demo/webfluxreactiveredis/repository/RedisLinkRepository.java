package com.demo.webfluxreactiveredis.repository;

import com.demo.webfluxreactiveredis.record.Link;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class RedisLinkRepository implements LinkRepository{

    private final ReactiveStringRedisTemplate operations;

    public RedisLinkRepository(ReactiveStringRedisTemplate operations) {
        this.operations = operations;
    }

    @Override
    public Mono<Link> save(Link link) {
        return operations.opsForValue()
                .set(link.key(), link.originalLink())
                .map(__ -> link);
    }

    @Override
    public Mono<Link> findByKey(String key) {
        return operations.opsForValue()
                .get(key)
                .map(result -> new Link(result, key));
    }
}
