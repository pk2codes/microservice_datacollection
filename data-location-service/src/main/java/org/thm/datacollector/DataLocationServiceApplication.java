package org.thm.datacollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories
public class DataLocationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataLocationServiceApplication.class, args);
	}
}
