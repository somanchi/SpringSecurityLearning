package sh.radical.basicauthtest.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.basicauthtest.exceptions.BasicauthtestCustomException;

@ResponseStatus
public class ParserException extends BasicauthtestCustomException {

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
