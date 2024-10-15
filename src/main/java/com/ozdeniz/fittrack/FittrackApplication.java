package com.ozdeniz.fittrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FittrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FittrackApplication.class, args);
	}

}
