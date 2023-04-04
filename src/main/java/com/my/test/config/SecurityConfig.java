package com.my.test.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.my.test.actuator.MyCustomActuatorEndPoint;

@Configuration
public class SecurityConfig {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authz -> {
			authz.requestMatchers(EndpointRequest.to(HealthEndpoint.class)).permitAll();
			authz.requestMatchers(EndpointRequest.to(MyCustomActuatorEndPoint.class)).authenticated();
			authz.requestMatchers(EndpointRequest.toAnyEndpoint()).authenticated();
		});

		http.httpBasic();

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		PasswordEncoder pswEncoder = passwordEncoder();
		List<UserDetails> users = new ArrayList<>();
		/**
		 * Need to enter authorities. o/w you will get an exception on startup complaining
		 * authorities are null
		 */
		users.add(User.builder().passwordEncoder(pswEncoder::encode)
				.username("yo").password("yoyo").roles("cool").build());
		users.add(User.builder().passwordEncoder(pswEncoder::encode)
				.username("test").password("test").roles("notcool").build());
		return new InMemoryUserDetailsManager(users);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
