package com.my.test;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping(path = "/api/v1")
public class MyController {

	private final Counter requestCounter;
	private final Random random;

	@Autowired
	public MyController(MeterRegistry registry) {
		requestCounter = Counter.builder("MyController-requestCounter")
				.description("MyController request counter")
				.register(registry);

		random = new Random();
	}

	@GetMapping("/welcome")
	public String welcome() {
		requestCounter.increment();
		// just some craze random thing
		return String.valueOf(random.nextDouble());
	}

}
