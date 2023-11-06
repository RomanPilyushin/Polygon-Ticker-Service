package com.example.restfulpolygon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailyOpenCloseService {

    private final RestTemplate restTemplate;

    @Autowired
    private DailyOpenCloseRepository repository;

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

    public List<DailyOpenClose> findAll() {
        return repository.findAll();
    }

    public Optional<DailyOpenClose> findById(Long id) {
        return repository.findById(id);
    }

    public DailyOpenClose save(DailyOpenClose dailyOpenClose) {
        return repository.save(dailyOpenClose);
    }

    public void update(DailyOpenClose dailyOpenClose) {
        repository.save(dailyOpenClose);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}



