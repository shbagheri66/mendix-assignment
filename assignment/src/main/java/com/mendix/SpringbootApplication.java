package com.mendix;


import org.apache.log4j.Logger;
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
		Logger.getLogger(SpringbootApplication.class).info("App Started!");
	}
}
