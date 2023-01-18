package com.nandy.rmm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.nandy.rmm.repository", mongoTemplateRef = CarMongoConfiguration.MONGO_TEMPLATE_REFERENCE)
public class CarMongoConfiguration {
	
	public static final String MONGO_TEMPLATE_REFERENCE="myMongoTemplate";
	

}
