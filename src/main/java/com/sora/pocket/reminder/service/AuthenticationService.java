package com.sora.pocket.reminder.service;

import com.sora.pocket.reminder.config.ApiConfig;
import com.sora.pocket.reminder.entity.TokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RestTemplate restTemplate;
    private final ApiConfig apiConfig;

    public String initialize() {

        // get request token
        //String consumerKey = System.getenv("POCKET_CONSUMER_KEY");
        String consumerKey = apiConfig.getConsumerKey();
        String getTokenUrl = UriComponentsBuilder.fromUriString(apiConfig.getTokenUrl()).toUriString();
        log.debug("getTokenUrl: {}", getTokenUrl);
        TokenRequest tokenRequest = new TokenRequest(consumerKey, apiConfig.getPocketUrl());

        String results = restTemplate.postForObject(getTokenUrl, tokenRequest, String.class);
        String[] args = results.split("=");
        String token = args[1];
        log.debug("token: {}", token);

        // redirect
        String redirectUrl =
                UriComponentsBuilder.fromUriString(apiConfig.getRedirectUrl())
                        .queryParam("request_token", token)
                        .queryParam("redirect_uri", apiConfig.getPocketUrl() + "?token=" + token)
                        .toUriString();
        log.debug("redirectUrl: {}", redirectUrl);
        return "redirect:" + redirectUrl;
    }
}
