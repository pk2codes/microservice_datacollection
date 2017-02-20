package thm.datacollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableEurekaClient
public class DataLoadServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataLoadServiceApplication.class, args);
	}

}
