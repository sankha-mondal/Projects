package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerAppApplication.class, args);
		System.out.println("Eureka Server up...Running on default port number 8761...");
	}

}

		/**
		 *   Dependencies: web, devtools, Eureka Server
		 *   Run as: Java Application
		 */


