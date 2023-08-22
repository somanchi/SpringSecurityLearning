package sh.radical.school.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.school.exceptions.SchoolCustomException;

@ResponseStatus
public class ValidationException extends SchoolCustomException {

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
