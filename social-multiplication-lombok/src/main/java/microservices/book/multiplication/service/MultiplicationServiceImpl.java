package microservices.book.multiplication.service;

import org.springframework.stereotype.Service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {
	
	private RandomGeneratorService randomGeneratorService;

	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomGeneratorService.generateRandomFactor();
		int factorB = randomGeneratorService.generateRandomFactor();
		return new Multiplication(factorA, factorB);
	}

	@Override
	public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
		return resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getFactorA()
				* resultAttempt.getMultiplication().getFactorB();
	}

}
