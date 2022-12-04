package com.emeritus.assignment.controller;

import com.emeritus.assignment.entity.AuthRequest;
import com.emeritus.assignment.service.SchoolUserDetailsService;
import com.emeritus.assignment.utility.JWTUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class JWTController {

        @Autowired
        private JWTUtility jwtUtility;

        @Autowired
        private SchoolUserDetailsService schoolUserDetailsService;



        @PostMapping("/token")
        public ResponseEntity<?> authenticate(Authentication authentication) throws Exception {
                System.out.println(authentication.getName());
        return ResponseEntity.ok(jwtUtility.generateToken(schoolUserDetailsService.loadUserByUsername(authentication.getName())));
        }

    }


