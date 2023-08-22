package sh.radical.school.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.school.exceptions.SchoolCustomException;

@ResponseStatus
public class EmployeeNotFound extends SchoolCustomException {

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
