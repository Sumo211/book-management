package com.leon.exercise;

import com.leon.exercise.model.User;
import com.leon.exercise.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class BookManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner initUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return event -> userRepository.save(User.builder()
                .email("test@gmail.com")
                .password(passwordEncoder.encode("12345678"))
                .firstName("Cong")
                .lastName("Nguyen")
                .build());
    }

}
