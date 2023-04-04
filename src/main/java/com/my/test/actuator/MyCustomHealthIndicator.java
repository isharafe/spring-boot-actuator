package com.my.test.actuator;

import java.util.Random;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
/**
 * The status of this indicator will appear under "health" end point.
 * indicator name will be "myCustom", derived from the class name
 */
public class MyCustomHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		boolean status = isThisServiceHealthy();
		if(status) {
			return Health.up()
					.withDetail("status", "doing good")
					.build();
		}

		return Health.down()
				.withDetail("status", "not doing well")
				.build();
	}

	private boolean isThisServiceHealthy() {
		return new Random().nextBoolean();
	}

}
