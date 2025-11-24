package com.urlshortening.backend.Controller;

import com.urlshortening.backend.DTO.LoginRequest;
import com.urlshortening.backend.Entity.Admins;
import com.urlshortening.backend.Entity.UrlMapping;
import com.urlshortening.backend.Security.JwtUtil;
import com.urlshortening.backend.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String token = adminService.getToken(loginRequest);
        if (token != null){
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Entry");
    }

    @GetMapping("/admin/links")
    public ResponseEntity<Page<UrlMapping>> urlListPage(Pageable pageable){
        return ResponseEntity.ok(adminService.urlPage(pageable));
    }
}
