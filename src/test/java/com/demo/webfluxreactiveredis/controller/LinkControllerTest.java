package com.demo.webfluxreactiveredis.controller;

import com.demo.webfluxreactiveredis.service.LinkService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@WebFluxTest(controllers = LinkController.class)
public class LinkControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LinkService linkService;

    @Test
    public void shrtensLink() {
        when(linkService.shortenLink("https://spring.io")).thenReturn(Mono.just("http://localhost:8080/abc123"));

        webTestClient.post()
                .uri("/link")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"link\":\"https://spring.io\"}")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.shortenedLink")
                .value(val -> Assertions.assertThat(val).isEqualTo("http://localhost:8080/abc123"));
    }
}
