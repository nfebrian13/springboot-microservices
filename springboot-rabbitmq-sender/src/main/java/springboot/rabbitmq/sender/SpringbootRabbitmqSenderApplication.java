package springboot.rabbitmq.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootRabbitmqSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqSenderApplication.class, args);
	}

}
