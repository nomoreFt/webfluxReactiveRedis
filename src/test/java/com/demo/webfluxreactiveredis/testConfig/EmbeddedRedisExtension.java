package com.demo.webfluxreactiveredis.testConfig;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

@TestConfiguration
public class EmbeddedRedisExtension {

    private RedisServer redisServer;
    @PostConstruct
    public void postConstruct() {
        redisServer = RedisServer.builder()
                .port(6380)
                .setting("maxmemory 128M")
                .build();

        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }

}