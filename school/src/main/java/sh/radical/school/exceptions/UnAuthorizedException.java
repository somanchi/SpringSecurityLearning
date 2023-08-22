package sh.radical.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends SchoolCustomException {
    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(String message, Throwable t) {
        super(message, t);
    }
}
