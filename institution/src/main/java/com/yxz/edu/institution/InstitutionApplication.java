package com.yxz.edu.institution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.yxz.edu.institution.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class InstitutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstitutionApplication.class, args);
	}

}
