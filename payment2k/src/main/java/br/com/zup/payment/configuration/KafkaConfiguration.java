package br.com.zup.payment.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.zup.shared.event.AbstractOrderEvent;
import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.spring.TracingConsumerFactory;
import io.opentracing.contrib.kafka.spring.TracingProducerFactory;

@Configuration
public class KafkaConfiguration {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrap;

	@Autowired
	private Tracer tracer;

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic approvedOrdersTopic() {
		return new NewTopic("approved-orders-payment", 1, (short) 1);
	}

	@Bean
	public NewTopic rejectedOrdersTopic() {
		return new NewTopic("rejected-orders-payment", 1, (short) 1);
	}

	@Bean
	public ProducerFactory kafkaProducerFactory() {

		Map<String, Object> configProps = new HashMap<>();

		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		ProducerFactory producerFactory = new DefaultKafkaProducerFactory<>(configProps);

		return new TracingProducerFactory(producerFactory, tracer);
	}

	@Bean
	public KafkaTemplate<String, AbstractOrderEvent> kafkaTemplate() {
		return new KafkaTemplate<String, AbstractOrderEvent>(kafkaProducerFactory());
	}

	@Bean
	public ConsumerFactory kafkaConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "order-group-id");
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		ConsumerFactory consumerFactory = new DefaultKafkaConsumerFactory<>(props);

		return new TracingConsumerFactory(consumerFactory, tracer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(kafkaConsumerFactory());
		return factory;
	}
}
