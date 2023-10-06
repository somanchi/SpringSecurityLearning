package sh.radical.selectallinteger.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.selectallinteger.exceptions.SelectallintegerCustomException;

@ResponseStatus
public class ParserException extends SelectallintegerCustomException {

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
