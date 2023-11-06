package com.example.restfulpolygon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DailyOpenCloseController {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Autowired
    private DailyOpenCloseService service;

    @Value("${polygon.api.key}")
    private String apiKey;

    @Autowired
    public DailyOpenCloseController(ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/daily-open-close/{ticker}/{date}")
    public ResponseEntity<?> getDailyOpenClose(@PathVariable String ticker, @PathVariable String date) {
        try {
            String url = String.format(
                    "https://api.polygon.io/v1/open-close/%s/%s?adjusted=true&apiKey=%s",
                    ticker, date, apiKey
            );

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
                DailyOpenClose response = objectMapper.readValue(responseEntity.getBody(), DailyOpenClose.class);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
            }
        } catch (HttpClientErrorException e) {
            // Handle client errors (4xx statuses)
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            // Log the exception and return a generic error response
            // Logger.log(e); // Replace with a real logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching data");
        }
    }

    @GetMapping
    public List<DailyOpenClose> getAllDailyOpenClose() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyOpenClose> getDailyOpenCloseById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DailyOpenClose createDailyOpenClose(@RequestBody DailyOpenClose dailyOpenClose) {
        return service.save(dailyOpenClose);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyOpenClose> updateDailyOpenClose(@PathVariable Long id, @RequestBody DailyOpenClose dailyOpenClose) {
        return service.findById(id)
                .map(existing -> {
                    dailyOpenClose.setId(existing.getId());
                    service.save(dailyOpenClose);
                    return ResponseEntity.ok(dailyOpenClose);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDailyOpenClose(@PathVariable Long id) {
        return service.findById(id)
                .map(entity -> {
                    service.delete(entity.getId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
