package nl.rabo.playground.cachingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class CachingTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingTestApplication.class, args);
	}

}
