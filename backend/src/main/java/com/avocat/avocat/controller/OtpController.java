package com.avocat.avocat.controller;

import com.avocat.avocat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class OtpController {

    @Autowired
    private UserService userService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String username) {
        String otp = userService.sendOtp(username);
        // Ideally, send the OTP to the user's email
        return ResponseEntity.ok("OTP sent: " + otp); // Change this to avoid exposing OTP
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String username, @RequestParam String otp) {
        boolean isValid = userService.verifyOtp(username, otp);
        if (isValid) {
            return ResponseEntity.ok("OTP verified successfully!");
        } else {
            return ResponseEntity.status(401).body("Invalid OTP.");
        }
    }
}
