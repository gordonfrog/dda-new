//package com.nandy.rmm.config;
//
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
//import com.mongodb.MongoClient;
//import com.nandy.rmm.event.CascadeSaveMongoEventListener;
//
//@Configuration	
//@EnableConfigurationProperties(MongoProperties.class)
//public class MongoConfiguration {
//	
//	private MongoProperties mongoProperties;
//
//	public MongoConfiguration(MongoProperties mongoProperties) {
//		this.mongoProperties = mongoProperties;
//	}
//	
//	@Bean
//	public CascadeSaveMongoEventListener listener() {
//		return new CascadeSaveMongoEventListener();
//	}
//	
//	@Primary
//	@Bean(name = CarMongoConfiguration.MONGO_TEMPLATE_REFERENCE	)
//	public MongoTemplate carMongoTemplate() {
//		return new MongoTemplate(new SimpleMongoDbFactory(
//				new MongoClient(mongoProperties.getHost(), mongoProperties.getPort()), mongoProperties.getDatabase()));
//	}
//	
//}
