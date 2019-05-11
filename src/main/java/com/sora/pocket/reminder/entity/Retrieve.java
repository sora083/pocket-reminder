package com.sora.pocket.reminder.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
/** @see https://github.com/local0125/PocketAPI-Retrieve-Parse-POJO */
public class Retrieve {
  private String status;
  private String complete;
  private Map<String, Item> list;
  private Object error;
}
