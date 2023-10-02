package sh.radical.basicauthtest.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.basicauthtest.exceptions.BasicauthtestCustomException;

@ResponseStatus
public class CarNotFound extends BasicauthtestCustomException {

	public CarNotFound() {
		super();
	}

	public CarNotFound(String message) {
		super(message);
	}

	public CarNotFound(String message, Throwable t) {
		super(message, t);
	}
}
