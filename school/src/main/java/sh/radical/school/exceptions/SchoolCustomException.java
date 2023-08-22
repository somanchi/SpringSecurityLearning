package sh.radical.school.exceptions;

import java.lang.RuntimeException;
import java.lang.Throwable;

public class SchoolCustomException extends RuntimeException {

	public SchoolCustomException() {
		super();
	}

	public SchoolCustomException(String message) {
		super(message);
	}

	public SchoolCustomException(String message, Throwable t) {
		super(message, t);
	}
}
