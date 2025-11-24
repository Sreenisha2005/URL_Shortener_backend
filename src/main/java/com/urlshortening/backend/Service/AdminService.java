package com.urlshortening.backend.Service;

import com.urlshortening.backend.DTO.LoginRequest;
import com.urlshortening.backend.Entity.Admins;
import com.urlshortening.backend.Entity.UrlMapping;
import com.urlshortening.backend.Repository.AdminRepository;
import com.urlshortening.backend.Repository.UrlMapRepository;
import com.urlshortening.backend.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UrlMapRepository urlRepo;
    private final AdminRepository adminRepo;
    private final PasswordEncoder encoder;

    public Page<UrlMapping> urlPage(Pageable pageable){
        return urlRepo.findAll(pageable);
    }

    public String getToken(LoginRequest loginRequest){
        Admins admin = adminRepo.findByUsername(loginRequest.getUsername()).orElse(null);
        if (admin != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", admin.getRole());
            boolean pwdMatches = encoder.matches(loginRequest.getPassword(), admin.getPassword());
            if (pwdMatches){
                return JwtUtil.generateToken(claims, admin.getUsername());
            }
        }
        return null;
    }

}
