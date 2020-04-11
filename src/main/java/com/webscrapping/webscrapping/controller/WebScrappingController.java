package com.webscrapping.webscrapping.controller;

import com.webscrapping.webscrapping.service.WebScrappingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class WebScrappingController {

    private final WebScrappingService webScrappingService;

    public WebScrappingController(WebScrappingService webScrappingService) {
        this.webScrappingService = webScrappingService;
    }

    @GetMapping("/open-graph-matadata-properties")
    public ResponseEntity<Map<String, String>> webScrappingOpenGraph(@RequestParam(value = "url") String url) throws IOException {
        return new ResponseEntity<>(webScrappingService.retrieveOpenGraphPropertyMetadata(url), HttpStatus.OK);
    }

    @GetMapping("twitter-matadata-properties")
    public ResponseEntity<Map<String, String>> webScrappingTwitter(@RequestParam(value = "url") String url) throws IOException {
        return new ResponseEntity<>(webScrappingService.retrieveTwitterPropertyMetadata(url), HttpStatus.OK);
    }
}
