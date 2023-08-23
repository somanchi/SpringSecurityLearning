package sh.radical.hostel.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.hostel.exceptions.HostelCustomException;

@ResponseStatus
public class ParserException extends HostelCustomException {

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
