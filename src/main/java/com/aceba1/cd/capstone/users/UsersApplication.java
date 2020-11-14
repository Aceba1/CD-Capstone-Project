package com.aceba1.cd.capstone.users;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class}) // Halts a strange port-listener seeking 27017
public class UsersApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(UsersApplication.class)
			.properties("server.port=5911")
			.build()
			.run(args);

		UserDBService.connect();
	}
}
