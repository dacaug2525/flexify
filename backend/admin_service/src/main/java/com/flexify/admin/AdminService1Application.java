package com.flexify.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AdminService1Application {

	public static void main(String[] args) {
		SpringApplication.run(AdminService1Application.class, args);
	}

}
