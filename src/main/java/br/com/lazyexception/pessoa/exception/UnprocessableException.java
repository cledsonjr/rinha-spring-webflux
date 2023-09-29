package br.com.lazyexception.pessoa.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UnprocessableException extends ResponseStatusException {

    public UnprocessableException() {
        this(HttpStatusCode.valueOf(422));
    }
    public UnprocessableException(HttpStatusCode status) {
        super(status);
    }

    public UnprocessableException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public UnprocessableException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public UnprocessableException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected UnprocessableException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
