package com.travelplanner.config;

import com.travelplanner.model.User;
import com.travelplanner.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            // Check if test user exists
            if (userRepository.findById(1L).isEmpty()) {
                User user = new User();
                user.setName("Test User");
                user.setUsername("testuser");
                user.setEmail("test@example.com");
                userRepository.save(user);
            }
        };
    }
} 