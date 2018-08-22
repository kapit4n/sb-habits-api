package com.habits.habits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HabitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitsApplication.class, args);
	}
}
