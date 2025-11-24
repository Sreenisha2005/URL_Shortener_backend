package com.urlshortening.backend.Controller;

import com.urlshortening.backend.DTO.LongUrl;
import com.urlshortening.backend.DTO.ShortCode;
import com.urlshortening.backend.Entity.UrlMapping;
import com.urlshortening.backend.Service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/url")
public class UrlController {

    private final UrlService service;

    @PostMapping
    public ResponseEntity<ShortCode> shortUrl(@RequestBody LongUrl longurl){
        return ResponseEntity.ok(ShortCode.builder()
                .shortcode(service.store(longurl.getLongurl()))
                .build());
    }

    @GetMapping("/{shortcode}")
    public ResponseEntity<?> urlRedirect(@PathVariable String shortcode){
        UrlMapping url = service.getByShortcode(shortcode);
        if (url == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(url.getLongurl()))
                .build();
    }
}
