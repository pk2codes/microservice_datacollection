package thm.datacollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thm.datacollector.model.OnlineRecording;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@RestController
public class MockedDataResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockedDataResourceApplication.class, args);
	}


	private double createRandomDouble(){
		final Random r = new Random();
		return r.nextDouble() * 100;
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

	@RequestMapping("/data/ivalue/activepower/{timebase}")
	public OnlineRecording getRandomIvalue(@PathVariable("timebase") final long tb) {
		return loadDataActivePowerValue(tb);
	}

	private OnlineRecording loadDataActivePowerValue(final long timeBaseMS) {
		final String DUMMY_IVALUE = "POWER_ACTIVE_IVAL_IVAL_IVAL";
		final Date end = new Date();
		final Date start = new Date();
		start.setTime(end.getTime() - timeBaseMS);
		final OnlineRecording onlineRecording = new OnlineRecording();
		onlineRecording.setStartTime(start);
		onlineRecording.setEndTime(end);
		onlineRecording.setType(DUMMY_IVALUE);
		onlineRecording.setValue(createRandomDouble());
		return onlineRecording;
	}
}
