package com.sora.pocket.reminder.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    
  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setMessageConverters(japaneseMessageConverters());
    restTemplate.getMessageConverters().addAll(defaultMessageConvertors());
    return restTemplate;
  }

  private static List<HttpMessageConverter<?>> japaneseMessageConverters() {
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    return messageConverters;
  }

  private static List<HttpMessageConverter<?>> defaultMessageConvertors() {
    return new HttpMessageConverters().getConverters();
  }
}
