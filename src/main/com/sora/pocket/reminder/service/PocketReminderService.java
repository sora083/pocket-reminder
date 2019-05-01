package com.sora.pocket.reminder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sora.pocket.reminder.config.ApiConfig;
import com.sora.pocket.reminder.entity.AuthorizeRequest;
import com.sora.pocket.reminder.entity.Retrieve;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class PocketReminderService {

  private final RestTemplate restTemplate;
  private final ObjectMapper mapper;
  private final ApiConfig apiConfig;

  public Retrieve fetch(String accessToken) {
    String fetchUrl = createRequestUrl(accessToken);
    String results = restTemplate.getForObject(fetchUrl, String.class);
    Retrieve response = null;
    try {
      response = mapper.readValue(results, Retrieve.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    log.info("response: {}", response);
    return response;
  }

  private String createRequestUrl(String accessToken) {
    //String consumerKey = System.getenv("POCKET_CONSUMER_KEY");
    String consumerKey = apiConfig.getConsumerKey();
    return UriComponentsBuilder.fromUriString(apiConfig.getGetUrl())
        .queryParam("consumer_key", consumerKey)
        .queryParam("access_token", accessToken)
        .queryParam("sort", "newest")
        .queryParam("count", "10")
        .toUriString();
  }

  // get access token
  public String auth(String token) {
    // authorize
    //String consumerKey = System.getenv("POCKET_CONSUMER_KEY");
    String consumerKey = apiConfig.getConsumerKey();
    String authorizeUrl = UriComponentsBuilder.fromUriString(apiConfig.getAuthUrl()).toUriString();
    log.info("authorizeUrl: {}", authorizeUrl);
    AuthorizeRequest authorizeRequest = new AuthorizeRequest(consumerKey, token);

    String response = restTemplate.postForObject(authorizeUrl, authorizeRequest, String.class);
    String[] res = response.split("&");
    return res[0].split("=")[1];
  }
}
