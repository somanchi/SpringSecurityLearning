package sh.radical.school.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.school.exceptions.SchoolCustomException;

@ResponseStatus
public class ParserException extends SchoolCustomException {

	public ParserException() {
		super();
	}

	public ParserException(String message) {
		super(message);
	}

	public ParserException(String message, Throwable t) {
		super(message, t);
	}
}
