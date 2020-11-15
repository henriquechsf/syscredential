package me.henrique.syscredential;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SyscredentialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyscredentialApplication.class, args);

	}

}
