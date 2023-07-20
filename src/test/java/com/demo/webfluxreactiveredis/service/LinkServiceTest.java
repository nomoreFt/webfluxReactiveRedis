package com.demo.webfluxreactiveredis.service;

import com.demo.webfluxreactiveredis.record.Link;
import com.demo.webfluxreactiveredis.repository.LinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class LinkServiceTest {

    private static final String BASE_URL = "http://some-domain.com/";
    @Mock
    private LinkRepository linkRepository;
    private LinkService linkService;

    @BeforeEach
    public void setUp() {

        linkService= new LinkService(BASE_URL, linkRepository);
        Mockito.when(linkRepository.save(Mockito.any()))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

    }

    @Test
    public void shortenLink() {
        StepVerifier.create(linkService.shortenLink("https://spring.io"))
                .expectNextMatches(key -> key != null &&
                        key.length() > 0 &&
                        key.startsWith("http://some-domain.com/"))
                .expectComplete()
                .verify();
    }
}