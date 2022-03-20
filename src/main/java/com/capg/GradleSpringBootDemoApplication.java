package com.capg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class GradleSpringBootDemoApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(GradleSpringBootDemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GradleSpringBootDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Hello");
	}
}
