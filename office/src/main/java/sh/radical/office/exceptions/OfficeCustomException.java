package sh.radical.office.exceptions;

import java.lang.RuntimeException;
import java.lang.Throwable;

public class OfficeCustomException extends RuntimeException {

	public OfficeCustomException() {
		super();
	}

	public OfficeCustomException(String message) {
		super(message);
	}

	public OfficeCustomException(String message, Throwable t) {
		super(message, t);
	}
}
