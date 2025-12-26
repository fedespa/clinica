package com.example.Clinica.common.errors;

import org.springframework.http.HttpStatus;

public class ScheduleInvalidException extends BaseException {
    public ScheduleInvalidException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
