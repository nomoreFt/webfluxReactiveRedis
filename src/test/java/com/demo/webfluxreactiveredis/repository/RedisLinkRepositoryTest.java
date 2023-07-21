package com.demo.webfluxreactiveredis.repository;

import com.demo.webfluxreactiveredis.record.Link;
import com.demo.webfluxreactiveredis.testConfig.EmbeddedRedisExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import redis.embedded.RedisServer;



@SpringBootTest(classes = {EmbeddedRedisExtension.class})
class RedisLinkRepositoryTest {


    private static redis.embedded.RedisServer redisServer;

    @Autowired
    private RedisLinkRepository redisLinkRepository;


    @Test
    public void returnsSameLinkAsArgument() {
        Link link = new Link("https://spring.io", "abc123");
        StepVerifier.create(redisLinkRepository.save(link))
                .expectNext(link)
                .verifyComplete();
    }

    @Test
    public void savesInRedis(){
        Link link = new Link("https://spring.io", "abc123");
        StepVerifier.create(redisLinkRepository.save(link)
                        .flatMap(__ -> redisLinkRepository.findByKey(link.key())))
                .expectNext(link)
                .verifyComplete();
    }
}