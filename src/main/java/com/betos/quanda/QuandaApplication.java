package com.betos.quanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuandaApplication.class, args);
	}

}
