package com.demo.webfluxreactiveredis.controller;

import com.demo.webfluxreactiveredis.service.LinkService;
import com.demo.webfluxreactiveredis.record.CreateLinkRequest;
import com.demo.webfluxreactiveredis.record.CreateLinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
}
