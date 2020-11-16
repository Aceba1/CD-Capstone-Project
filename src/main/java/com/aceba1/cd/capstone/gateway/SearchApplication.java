package com.aceba1.cd.capstone.gateway;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class}) // Halts a strange port-listener seeking 27017
public class SearchApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SearchApplication.class)
			.properties("server.port=5910")
			.build()
			.run(args);
	}
}