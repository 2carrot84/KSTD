package com._2carrot84.kstd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KstdApplication {

	public static void main(String[] args) {
		SpringApplication.run(KstdApplication.class, args);
	}

}
