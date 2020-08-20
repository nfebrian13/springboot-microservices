package microservices.book.multiplication;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.service.MultiplicationServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class SocialMultiplicationLombokApplicationTests {

	@Autowired
	private MultiplicationServiceImpl multiplicationServiceImpl;

	@Test
	void contextLoads() {
	}

	@Test
	public void checkCorrectAttemptTest() {
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john_doe");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000);
		// when
		boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);
		// assert
		assertThat(attemptResult).isTrue();
	}

	@Test
	public void checkWrongAttemptTest() {
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john_doe");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010);
		// when
		boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);
		// assert
		assertThat(attemptResult).isFalse();
	}
}
