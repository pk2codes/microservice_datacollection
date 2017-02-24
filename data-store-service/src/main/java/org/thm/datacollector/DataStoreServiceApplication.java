package org.thm.datacollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.thm.datacollector.domain.Application;

@SpringBootApplication
public class DataStoreServiceApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext ctx = SpringApplication.run(DataStoreServiceApplication.class, args);
		Application app = (Application) ctx.getBean("application");
		try {
			app.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
