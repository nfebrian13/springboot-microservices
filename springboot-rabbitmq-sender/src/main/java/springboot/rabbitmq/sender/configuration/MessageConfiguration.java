package springboot.rabbitmq.sender.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

	@Value("${EXCHANGE_NAME}") private String EXCHANGE_NAME;
	@Value("${QUEUE_GENERIC_NAME}") private String QUEUE_GENERIC_NAME;
	@Value("${QUEUE_SPECIFIC_NAME}") private String QUEUE_SPECIFIC_NAME;
	@Value("${ROUTING_KEY}") private String ROUTING_KEY;

	@Bean
	public TopicExchange appExchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue appQueueGeneric() {
		return new Queue(QUEUE_GENERIC_NAME);
	}

	@Bean
	public Queue appQueueSpecific() {
		return new Queue(QUEUE_SPECIFIC_NAME);
	}

	@Bean
	public Binding declareBindingGeneric() {
		return BindingBuilder.bind(appQueueGeneric()).to(appExchange()).with(ROUTING_KEY);
	}

	@Bean
	public Binding declareBindingSpecific() {
		return BindingBuilder.bind(appQueueSpecific()).to(appExchange()).with(ROUTING_KEY);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
