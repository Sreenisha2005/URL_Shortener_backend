package com.urlshortening.backend.Repository;

import com.urlshortening.backend.Entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMapRepository extends JpaRepository<UrlMapping, Long>{
    UrlMapping findByLongurl(String longurl);
    UrlMapping findByShortcode(String shortcode);

}
