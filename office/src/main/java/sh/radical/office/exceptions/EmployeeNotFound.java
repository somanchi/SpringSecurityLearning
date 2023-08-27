package sh.radical.office.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.office.exceptions.OfficeCustomException;

@ResponseStatus
public class EmployeeNotFound extends OfficeCustomException {

	public EmployeeNotFound() {
		super();
	}

	public EmployeeNotFound(String message) {
		super(message);
	}

	public EmployeeNotFound(String message, Throwable t) {
		super(message, t);
	}
}
