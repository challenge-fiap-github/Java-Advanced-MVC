package com.java.odontovisionMVC;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class OdontovisionMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdontovisionMvcApplication.class, args);
	}

}
