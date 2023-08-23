package sh.radical.hostel.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.hostel.exceptions.HostelCustomException;

@ResponseStatus
public class ValidationException extends HostelCustomException {

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
