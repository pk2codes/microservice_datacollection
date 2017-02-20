package thm.datacollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@RestController
public class MockedDataResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockedDataResourceApplication.class, args);
	}


	private double createRandomDouble(){
		final Random r = new Random();
		return r.nextDouble();
	}

	private ExampleData loadDataWithRandomValue() {
		final ExampleData d = new ExampleData();
		d.setValue(createRandomDouble());
		d.setType("unknown");
		return d;
	}


	@RequestMapping("/data")
	public ExampleData getRandomData() {
		return loadDataWithRandomValue();
	}

	@RequestMapping("/data/{type}")
	public ExampleData getRandomDataWithType(@PathVariable("type") final String param) {
		final ExampleData d = loadDataWithRandomValue();
		d.setType(param);
		return d;
	}
}
