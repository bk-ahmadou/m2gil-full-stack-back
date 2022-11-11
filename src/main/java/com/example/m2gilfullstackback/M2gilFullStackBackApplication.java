package com.example.m2gilfullstackback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.m2gilfullstackback.repositories")
@EntityScan(basePackages = {"com.example.m2gilfullstackback.entities"})
public class M2gilFullStackBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(M2gilFullStackBackApplication.class, args);
    }

}
