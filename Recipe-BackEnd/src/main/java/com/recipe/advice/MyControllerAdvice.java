package com.recipe.advice;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.recipe.exception.ErrorDetails;
import com.recipe.exception.ListEmptyException;
import com.recipe.exception.RecipeNotFoundException;

/**
 * The MyControllerAdvice class is used to handle all exception. Here we handle
 * {@ Link RecipeNotFoundException} custom {@link Exception} Here we handle {@
 * Link ListEmptyException} custom {@link Exception} Here we handle {@ Link
 * handleMethodArgumentNotValid} for input {@link Valid}
 */
@RestControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

	// handle specific Exception for entity not found
	@ExceptionHandler(RecipeNotFoundException.class)
	public ResponseEntity<?> RecipeNotFoundEceptionHandler(RecipeNotFoundException notFoundException,
			WebRequest webRequest) {
		logger.warn("inside class MycontollerAdvice!! RecipeNotFoundEceptionHandler!!");
		ErrorDetails errorDetails = new ErrorDetails(notFoundException.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// handle custom Exception for EmptyList
	@ExceptionHandler(ListEmptyException.class)
	public ResponseEntity<?> ListEmptyExceptionHandler(ListEmptyException notFoundException, WebRequest webRequest) {

		logger.warn("Inside class MycontollerAdvice!! ListEmptyExceptionHandler!!");
		ErrorDetails errorDetails = new ErrorDetails(notFoundException.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// handle custom valid Exception of entity
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.warn("Inside class MycontollerAdvice!! handleMethodArgumentNotValid!!");
		ErrorDetails errorMessage = new ErrorDetails("Valid Error in Input",
				ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

}
