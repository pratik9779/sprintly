package com.sprintly.sprintly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@CrossOrigin(origins = "*")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SprintlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintlyApplication.class, args);
	}
	

}
