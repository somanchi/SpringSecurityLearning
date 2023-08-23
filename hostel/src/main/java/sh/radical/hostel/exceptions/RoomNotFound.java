package sh.radical.hostel.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.hostel.exceptions.HostelCustomException;

@ResponseStatus
public class RoomNotFound extends HostelCustomException {

	public RoomNotFound() {
		super();
	}

	public RoomNotFound(String message) {
		super(message);
	}

	public RoomNotFound(String message, Throwable t) {
		super(message, t);
	}
}
