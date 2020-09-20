package springboot.rabbitmq.sender.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import springboot.rabbitmq.sender.message.CustomMessage;

import java.util.Random;

@Service
public class CustomMessageSender {

	private static final Logger log = LoggerFactory.getLogger(CustomMessageSender.class);

	@Value("${EXCHANGE_NAME}") private String EXCHANGE_NAME;
	@Value("${ROUTING_KEY}") private String ROUTING_KEY;

	/**
	 * RabbitTemplate
	 * Digunakan untuk mengkonversi dan mengirim pesan menggunakan RabbitMQ.
	 * RabbitTemplate adalah kelas helper seperti kelas Template lainnya
	 * yang ada di Spring (seperti JdbcTemplate, RestTemplate, dll.)
	 *  **/
	private final RabbitTemplate rabbitTemplate;

	public CustomMessageSender(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	/** Exchange dan Routing key 
	 * Exchange key digunakan untuk mengirim melalui channel tertentu (exchange). 
	 * Routing key digunakan untk menyaring siapa yg dapat menerima pesan tersebut.
	 * **/
	@Scheduled(fixedDelay = 3000L)
	public void sendMessage() {
		CustomMessage message = new CustomMessage("Hello there!", new Random().nextInt(50), false);
		log.info("Sending message...");
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
		System.out.println("");
	}
	
}
