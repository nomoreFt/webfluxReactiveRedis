package com.demo.webfluxreactiveredis.controller;

import com.demo.webfluxreactiveredis.service.LinkService;
import com.demo.webfluxreactiveredis.record.CreateLinkRequest;
import com.demo.webfluxreactiveredis.record.CreateLinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;


    @PostMapping("/link")
    Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest request) {
        return linkService.shortenLink(request.link())
                .map(CreateLinkResponse::new);
    }

    @GetMapping("/{key}")
    Mono<ResponseEntity<Object>> getLink(@PathVariable String key) {
        return linkService.getOriginalLink(key)
                .map(link -> ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                        .header("Location", link.originalLink())
                        .build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
