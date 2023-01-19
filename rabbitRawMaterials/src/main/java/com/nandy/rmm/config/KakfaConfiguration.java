//package com.nandy.rmm.config;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import com.nandy.rmm.model.Car;
//import com.nandy.rmm.model.User;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KakfaConfiguration {
//	
//	@Value("${kafka.bootstrap.servers}")
//	private String bootstrapServers;
//	
//    @Bean
//    public ProducerFactory<String, User> producerFactory1() {
//        Map<String, Object> config = new HashMap<>();
//
//        //config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//    
//    @Bean
//    public ProducerFactory<String, String> producerFactory2() {
//        Map<String, Object> config = new HashMap<>();
//
//        //config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//    
//    @Bean
//    public ProducerFactory<String, Car> producerFactory3() {
//        Map<String, Object> config = new HashMap<>();
//
//        //config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//
//
//    @Bean
//    public KafkaTemplate<String, User> kafkaTemplate1() {
//        return new KafkaTemplate<>(producerFactory1());
//    }
//    
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate2() {
//        return new KafkaTemplate<>(producerFactory2());
//    }
//
//    @Bean
//    public KafkaTemplate<String, Car> kafkaTemplate3() {
//        return new KafkaTemplate<>(producerFactory3());
//    }
//
//}
