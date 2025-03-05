package com.example.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import com.example.twitter.PublicMetrics;




@Entity
@Table(name = "twitter_user")
@XmlRootElement(name = "user")
@JsonIgnoreProperties(ignoreUnknown =true)
public class TwitterUser {

    @Id
    @Column(length = 255, nullable = false)
    @JsonProperty("id")
    private String id;

    @Column(length = 255)
    @JsonProperty("name")
    private String name;

    @Column(length = 255, unique = true)
    @JsonProperty("username")
    private String username;

    @Column(length = 255)
    @JsonProperty("created_at")
    private String createdAt;

    @Lob
    @JsonProperty("description")
    private String description;

    private int declare_num;

    @Embedded
    @JsonProperty("public_metrics")  // Twitter API 응답과 매핑
    private PublicMetrics publicMetrics;


    public TwitterUser() {}

    public TwitterUser(String id, String name, String username, String createdAt, String description, int declare_num,
                        PublicMetrics publicMetrics) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.createdAt = createdAt;
        this.description = description;
        this.declare_num = declare_num;
        this.publicMetrics = publicMetrics;
    }

//
//    @JsonProperty("data") // data 필드를 처리할 수 있도록 매핑
//    private TwitterUser data; // data 필드를 TwitterUser로 처리

    // public 필드 또는 getter/setter가 있어야 JAXB가 접근할 수 있습니다.

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeclare_num() {
        return declare_num;
    }

    public void setDeclare_num(int declare_num) {
        this.declare_num = declare_num;
    }

    public PublicMetrics getPublicMetrics() {
        return publicMetrics;
    }

    public void setPublicMetrics(PublicMetrics publicMetrics) {
        this.publicMetrics = publicMetrics;
    }

}