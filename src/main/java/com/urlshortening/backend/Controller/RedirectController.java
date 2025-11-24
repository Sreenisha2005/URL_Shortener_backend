package com.urlshortening.backend.Controller;

import com.urlshortening.backend.Entity.UrlMapping;
import com.urlshortening.backend.Service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RedirectController {

    private final UrlService service;

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
