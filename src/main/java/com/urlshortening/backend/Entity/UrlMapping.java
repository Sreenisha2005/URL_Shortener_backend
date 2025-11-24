package com.urlshortening.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String longurl;

    @Column(unique = true)
    private String shortcode;

    @Builder
    public UrlMapping(String longurl, String shortcode){
        this.longurl = longurl;
        this.shortcode = shortcode;
    }
}
