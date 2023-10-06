package sh.radical.selectallinteger.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.selectallinteger.exceptions.SelectallintegerCustomException;

@ResponseStatus
public class ValidationException extends SelectallintegerCustomException {

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
