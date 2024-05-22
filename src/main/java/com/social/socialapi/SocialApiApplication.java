package com.social.socialapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SocialApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialApiApplication.class, args);
	}

}
