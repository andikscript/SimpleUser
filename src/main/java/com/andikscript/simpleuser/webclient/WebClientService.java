package com.andikscript.simpleuser.webclient;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/SpringAndik").build();
    }

    public String callShow() {
        return this.webClient.get().uri("/andik")
                .retrieve().bodyToMono(String.class).block();
    }
}
