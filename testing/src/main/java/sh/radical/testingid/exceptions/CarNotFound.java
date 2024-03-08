package sh.radical.testingid.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.testingid.exceptions.TestingidCustomException;

@ResponseStatus
public class CarNotFound extends TestingidCustomException {

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
