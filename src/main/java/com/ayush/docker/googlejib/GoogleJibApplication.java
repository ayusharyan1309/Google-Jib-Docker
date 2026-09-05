package com.ayush.docker.googlejib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GoogleJibApplication {

	@GetMapping("/hi")
	public String hi() {
		return "google jib is running and pushed";
	}

	public static void main(String[] args) {
		SpringApplication.run(GoogleJibApplication.class, args);
	}

}
