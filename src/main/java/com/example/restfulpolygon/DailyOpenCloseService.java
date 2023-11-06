package com.example.restfulpolygon;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DailyOpenCloseService {

    private final RestTemplate restTemplate;

    @Value("${polygon.api.key}")
    private String apiKey;

    public DailyOpenCloseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DailyOpenClose getDailyOpenClose(String ticker, String date) {
        String url = String.format("https://api.polygon.io/v1/open-close/%s/%s?adjusted=true&apiKey=%s",
                ticker, date, apiKey);

        ResponseEntity<DailyOpenClose> response = restTemplate.getForEntity(url, DailyOpenClose.class);
        return response.getBody();
    }
}



