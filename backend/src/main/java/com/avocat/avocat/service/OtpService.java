package com.avocat.avocat.controller;

import com.avocat.avocat.service.EmailService;
import com.avocat.avocat.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Authenticate username and password (for now, assume valid login for testing)
        
        // Generate OTP
        String otp = otpService.generateOtp(username);

        // Send OTP email
        String subject = "Your Secure OTP for Login Verification";

String message = """
    Hello,

    To complete your login, please use the following one-time password (OTP):

    OTP Code: %s

    This code is valid for the next 10 minutes. For your security, do not share this code with anyone.

    Thank you,
    The App Team
    """.formatted(otp);

emailService.sendOtpEmail(username, subject, message);

        return "OTP sent to your email.";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String username, @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(username, otp);
        if (isValid) {
            otpService.clearOtp(username);  // Clear OTP after successful validation
            return "Login successful!";
        } else {
            return "Invalid OTP. Please try again.";
        }
    }
}
