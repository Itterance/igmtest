package com.igm.igmtest.endpoint_3b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/3b/data")
public class EbController {

    private final EbService ebService;

    @Autowired
    public EbController(EbService ebService) {
        this.ebService = ebService;
    }

    @GetMapping
    public ResponseEntity<String> getData() {
        try {
            String data = ebService.getData();
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
    }
}
