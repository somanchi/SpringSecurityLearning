package sh.radical.hostel.exceptions;

import java.lang.RuntimeException;
import java.lang.Throwable;

public class HostelCustomException extends RuntimeException {

	public HostelCustomException() {
		super();
	}

	public HostelCustomException(String message) {
		super(message);
	}

	public HostelCustomException(String message, Throwable t) {
		super(message, t);
	}
}
