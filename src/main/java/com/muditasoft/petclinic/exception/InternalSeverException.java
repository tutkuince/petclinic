package com.muditasoft.petclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalSeverException extends RuntimeException {

    public InternalSeverException(Throwable cause) {
        super(cause);
    }
}
