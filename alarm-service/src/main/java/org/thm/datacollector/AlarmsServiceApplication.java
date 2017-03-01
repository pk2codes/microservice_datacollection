package org.thm.datacollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.thm.datacollector.model.Application;

@SpringBootApplication
public class AlarmsServiceApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext ctx = SpringApplication.run(AlarmsServiceApplication.class, args);
		Application app = (Application) ctx.getBean("application");
		try {
			app.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(AlarmsServiceApplication.class, args);

	}
}
