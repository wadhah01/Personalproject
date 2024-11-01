package com.avocat.avocat.service;

import com.avocat.avocat.model.User;
import com.avocat.avocat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

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
        String otp = String.valueOf((int)(Math.random() * 9000) + 1000); // Generate a random OTP
        otpStore.put(username, otp); // Save the OTP for verification

        // For demonstration, we're just returning the OTP
        return otp; // Replace this with actual email OTP sending logic
    }

    public boolean verifyOtp(String username, String otp) {
        String storedOtp = otpStore.get(username);
        return storedOtp != null && storedOtp.equals(otp);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
