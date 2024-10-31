package com.avocat.avocat.service;

import com.avocat.avocat.model.User;
import com.avocat.avocat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // In-memory storage for OTPs
    private Map<String, String> otpStore = new HashMap<>();

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));  // Encrypt the password
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String sendOtp(String username) {
        // Generate a random OTP
        String otp = String.valueOf((int)(Math.random() * 9000) + 1000);
        // Save the OTP with the username for verification
        otpStore.put(username, otp);

        // Here, you should integrate your email service to send the OTP
        // For demonstration, we're just returning the OTP
        return otp; // Change this to actually send the OTP via email
    }

    public boolean verifyOtp(String username, String otp) {
        String storedOtp = otpStore.get(username);
        return storedOtp != null && storedOtp.equals(otp);
    }
}
