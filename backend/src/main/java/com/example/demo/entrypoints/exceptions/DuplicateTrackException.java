package com.example.demo.entrypoints.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "This Track is already saved in Database")
public class DuplicateTrackException extends RuntimeException {
}
