package com.aceba1.cd.capstone.gateway;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class}) // Halts a strange port-listener seeking 27017
public class SearchApplication {

  //TODO: Focus on this later!

	public static void main(String[] args) {
		new SpringApplicationBuilder(SearchApplication.class)
			.properties("server.port=5910")
			.build()
			.run(args);
	}
}
