package com.jtj.jwtm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.jtj.jwtm.repository")
@SpringBootApplication
public class JwtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtmApplication.class, args);
    }
}
