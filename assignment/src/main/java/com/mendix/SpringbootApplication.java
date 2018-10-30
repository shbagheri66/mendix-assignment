package com.mendix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.mendix"})
@EntityScan(basePackages = {"com.mendix"})
public class SpringbootApplication extends SpringBootServletInitializer {	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
		System.out.println("App Started!");
	}
}
