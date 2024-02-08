package com.example.potatotilnewsfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PotatoTilNewsfeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(PotatoTilNewsfeedApplication.class, args);
    }

}
