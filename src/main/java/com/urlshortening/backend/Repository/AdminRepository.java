package com.urlshortening.backend.Repository;

import com.urlshortening.backend.Entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Long> {
    Optional<Admins> findByUsername(String username);
}
