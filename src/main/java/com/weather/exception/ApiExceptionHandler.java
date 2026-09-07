package com.weather.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 */
@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ProblemDetail invalidQuery(IllegalArgumentException exception) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail invalidReading(MethodArgumentNotValidException exception) {
		var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid reading");
		problem.setProperty("errors", exception.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).sorted().toList());
		return problem;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ProblemDetail duplicate() {
		return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
				"A reading already exists for this sensor, metric and timestamp");
	}
}
