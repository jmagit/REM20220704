package com.example;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exceptions.BadRequestException;
import com.example.exceptions.NotFoundException;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ApiExceptionHandler {
	public static class ErrorMessage implements Serializable {
		private static final long serialVersionUID = 1L;
		private String error, message;
		public ErrorMessage(String error, String message) {
			this.error = error;
			this.message = message;
		}
		public String getError() { return error; }
		public void setError(String error) { this.error = error; }
		public String getMessage() { return message; }
		public void setMessage(String message) { this.message = message; }
	}

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ BadRequestException.class })
    public ErrorMessage badRequest(Exception exception) {
        return new ErrorMessage(exception.getMessage(), "");
    }
}