package com.bonheure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BonheureApplication {

    public static void main(String[] args) {
    	//test
        SpringApplication.run(BonheureApplication.class, args);
    }

}
