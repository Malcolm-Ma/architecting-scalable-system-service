package com.acs.elearn.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User doesn't exist.")
public class UserNullException extends Exception {
    private static final long serialVersionUID = 1L;
}
