package com.sora.pocket.reminder.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Authorize {
  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("username")
  private String userName;
}
