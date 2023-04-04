package com.my.test.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "my-custom-data")
public class MyCustomActuatorEndPoint {

	@ReadOperation
	public Map<String, String> myCustomEndPoint(@Nullable String somedata) {
		Map<String, String> map = new HashMap<>();
		map.put("data", somedata);
		map.put("random-data", Double.toString(Math.random()));
		return map;
	}
}
