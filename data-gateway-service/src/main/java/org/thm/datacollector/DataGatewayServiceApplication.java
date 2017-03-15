package org.thm.datacollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataGatewayServiceApplication.class, args);
	}
}
