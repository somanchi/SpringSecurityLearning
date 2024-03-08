package sh.radical.testingid.exceptions;

import java.lang.RuntimeException;
import java.lang.Throwable;

public class TestingidCustomException extends RuntimeException {

	public TestingidCustomException() {
		super();
	}

	public TestingidCustomException(String message) {
		super(message);
	}

	public TestingidCustomException(String message, Throwable t) {
		super(message, t);
	}
}
