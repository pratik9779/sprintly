package com.sprintly.sprintly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SprintlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintlyApplication.class, args);
	}

}
