package com.example.twitter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

/// 매개변수 username을 받아 해당 계정 정보를 twitter로부터 받아옴
@Service
public class TwitterService {
    private final String BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAOYhzgEAAAAAoHpfGofpHhFpSqYA141RQp3D8q4%3DPGpBpVtber3cjrvsHLjg0LtSo1JhJDP6h93gBMhqEpNGXuyekn";
    private final String BASE_URL = "https://api.twitter.com/2/users/by/username/";

    public String getUserInfo(String username) {
        String url = BASE_URL + username + "?user.fields=created_at,description,public_metrics";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);


        return response.getBody();
    }
}
