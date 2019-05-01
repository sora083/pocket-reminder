package com.sora.pocket.reminder.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizeRequest {
    @JsonProperty("consumer_key")
    private String consumerKey;
    private String code;
}
