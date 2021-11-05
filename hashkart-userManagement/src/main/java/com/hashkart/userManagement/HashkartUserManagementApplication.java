package com.hashkart.userManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HashkartUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HashkartUserManagementApplication.class, args);
	}

}
