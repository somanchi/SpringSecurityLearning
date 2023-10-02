package sh.radical.basicauthtest.exceptions;

import java.lang.RuntimeException;
import java.lang.Throwable;

public class BasicauthtestCustomException extends RuntimeException {

	public BasicauthtestCustomException() {
		super();
	}

	public BasicauthtestCustomException(String message) {
		super(message);
	}

	public BasicauthtestCustomException(String message, Throwable t) {
		super(message, t);
	}
}
