package microservices.book.multiplication.service;

import java.util.List;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationService {
	/**
	 * Generates a random {@link Multiplication} object.
	 *
	 * @return a multiplication of randomly generated numbers
	 */
	Multiplication createRandomMultiplication();

	/**
	 * @return true if the attempt matches the result of the multiplication, false
	 *         otherwise.
	 */
	boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);

	/**
	 * Gets the statistics for a given user.
	 *
	 * @param userAlias the user's alias
	 * @return a list of {@link MultiplicationResultAttempt} objects, being the past
	 *         attempts of the user.
	 */
	List<MultiplicationResultAttempt> getStatsForUser(final String userAlias);
}
