package sh.radical.selectallinteger.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.selectallinteger.exceptions.SelectallintegerCustomException;

@ResponseStatus
public class StundentNotFound extends SelectallintegerCustomException {

	public StundentNotFound() {
		super();
	}

	public StundentNotFound(String message) {
		super(message);
	}

	public StundentNotFound(String message, Throwable t) {
		super(message, t);
	}
}
