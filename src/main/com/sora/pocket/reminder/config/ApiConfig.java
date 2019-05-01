package com.sora.pocket.reminder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pocket")
@Data
public class ApiConfig {
  private String consumerKey;
  private String getUrl;
  private String tokenUrl;
  private String redirectUrl;
  private String authUrl;
  private String pocketUrl;
  
//  public String getConsumerKey() {
//    this.consumerKey = System.getenv("POCKET_CONSUMER_KEY");
//    return this.consumerKey;
//  }
}
