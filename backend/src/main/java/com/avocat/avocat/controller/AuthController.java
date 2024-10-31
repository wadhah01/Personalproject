package com.avocat.avocat.controller;

import com.avocat.avocat.model.User;
import com.avocat.avocat.service.UserService;
import com.avocat.avocat.service.OtpService; // Import the OtpService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService; // Inject the OtpService

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestParam String username, @RequestParam String password) {
        User newUser = userService.registerUser(username, password);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String otp, @RequestParam String username) {
        boolean isOtpValid = otpService.validateOtp(username, otp);

        if (isOtpValid) {
            return ResponseEntity.ok("OTP verification successful! You are now logged in.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Invalid OTP. Please try again.");
        }
    }
    
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String username, @RequestParam String otp) {
        boolean isValidOtp = userService.verifyOtp(username, otp);
        if (isValidOtp) {
            return ResponseEntity.ok("OTP verification successful!");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
    }
}
