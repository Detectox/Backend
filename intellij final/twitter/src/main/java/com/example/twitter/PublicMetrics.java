package com.example.twitter;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class PublicMetrics {

    @JsonProperty("followers_count")
    private int follower;

    @JsonProperty("following_count")
    private int following;

    @JsonProperty("tweet_count")
    private int tweet;

    @JsonProperty("listed_count")
    private int listed;

    @JsonProperty("like_count")
    private int likes;

    @JsonProperty("media_count")
    private int media;

    // 기본 생성자
    public PublicMetrics() {}

    public PublicMetrics(int follower, int following, int tweet, int listed, int like, int media) {
        this.follower = follower;
        this.following = following;
        this.tweet = tweet;
        this.listed = listed;
        this.likes = like;
        this.media = media;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getTweet() {
        return tweet;
    }

    public void setTweet(int tweet) {
        this.tweet = tweet;
    }

    public int getListed() {
        return listed;
    }

    public void setListed(int listed) {
        this.listed = listed;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }
}
