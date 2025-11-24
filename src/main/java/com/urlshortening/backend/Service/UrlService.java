package com.urlshortening.backend.Service;

import com.urlshortening.backend.Entity.UrlMapping;
import com.urlshortening.backend.Repository.UrlMapRepository;
import com.urlshortening.backend.Utils.HashUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlMapRepository repository;
    private final HashUrl hashUrl;

    public String store(String longurl){
        UrlMapping url = repository.findByLongurl(longurl);
        if (url == null){
            url = UrlMapping.builder()
                    .longurl(longurl)
                    .shortcode(hashUrl.generate(longurl))
                    .build();

            repository.save(url);
        }
        return url.getShortcode();

    }

    public UrlMapping getByShortcode(String shortcode){
        return repository.findByShortcode(shortcode);

    }
}
