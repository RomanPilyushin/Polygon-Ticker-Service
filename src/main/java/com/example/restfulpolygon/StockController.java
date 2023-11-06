package com.example.restfulpolygon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final DailyOpenCloseService service;

    @Autowired
    public StockController(DailyOpenCloseService service) {
        this.service = service;
    }

    @GetMapping("/open-close/{ticker}/{date}")
    public DailyOpenClose getDailyOpenClose(@PathVariable String ticker, @PathVariable String date) {
        return service.getDailyOpenClose(ticker, date);
    }
}

