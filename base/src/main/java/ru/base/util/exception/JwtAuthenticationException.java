package ru.base.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    private HttpStatus HttpStatus;
    
    public JwtAuthenticationException(String msg) {
        super(msg);
    }

    /**
     * @return HttpStatus return the HttpStatus
     */
    public HttpStatus getHttpStatus() {
        return HttpStatus;
    }


}
