package sh.radical.selectallinteger.exceptions;

import java.lang.RuntimeException;
import java.lang.Throwable;

public class SelectallintegerCustomException extends RuntimeException {

	public SelectallintegerCustomException() {
		super();
	}

	public SelectallintegerCustomException(String message) {
		super(message);
	}

	public SelectallintegerCustomException(String message, Throwable t) {
		super(message, t);
	}
}
