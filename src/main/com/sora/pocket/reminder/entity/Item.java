package com.sora.pocket.reminder.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @JsonProperty("item_id")
    private String itemId;
    @JsonProperty("given_id")
    private String givenId;
    @JsonProperty("given_url")
    private String givenUrl;
    @JsonProperty("given_title")
    private String givenTitle;
    @JsonProperty("resolved_id")
    private String resolvedId;
    @JsonProperty("resolved_url")
    private String resolvedUrl;
    @JsonProperty("resolved_title")
    private String resolvedTitle;
    private String excerpt;
    @JsonProperty("top_image_url")
    private String topImageUrl;

//    public String favorite;
//    public String status;
//    public String time_added;
//    public String time_updated;
//    public String time_read;
//    public String time_favorited;
//    public String sort_id;
//    public String is_article;
//    public String is_index;
//    public String has_video;
//    public String has_image;
//    public String word_count;
}
