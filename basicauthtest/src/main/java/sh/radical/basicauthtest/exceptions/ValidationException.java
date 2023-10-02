package sh.radical.basicauthtest.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.basicauthtest.exceptions.BasicauthtestCustomException;

@ResponseStatus
public class ValidationException extends BasicauthtestCustomException {

	public ValidationException() {
		super();
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(String message, Throwable t) {
		super(message, t);
	}
}
