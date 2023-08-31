package com.example.pdf.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



// This line marks the beginning of the Spring Boot application class. 
// It includes the @SpringBootApplication annotation, 
// which is used to enable Spring Boot auto-configuration and component scanning.
@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoApplication {

	public static void main(String[] args) {
		// The SpringApplication.run() method starts the Spring application by creating an ApplicationContext,
		// registering any necessary beans, and then starting the Tomcat server. 
		// The Tomcat server listens for incoming HTTP requests and dispatches them to the appropriate Spring controller.
		SpringApplication.run(DemoApplication.class, args);
	}

}
