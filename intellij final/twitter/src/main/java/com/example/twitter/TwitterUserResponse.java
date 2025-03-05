package com.example.twitter;

import com.example.twitter.TwitterUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterUserResponse {

    @JsonProperty("data")
    private TwitterUser data;  // "data" 안에 있는 객체를 TwitterUser로 매핑

    public TwitterUser getData() {
        return data;
    }

    public void setData(TwitterUser data) {
        this.data = data;
    }
}
