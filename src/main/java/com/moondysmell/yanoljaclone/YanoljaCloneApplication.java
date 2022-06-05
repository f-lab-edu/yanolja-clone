package com.moondysmell.yanoljaclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YanoljaCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(YanoljaCloneApplication.class, args);
	}

}
