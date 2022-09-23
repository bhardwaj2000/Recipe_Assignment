package com.recipe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * {@link ListEmptyException} this class to handle custom {@link Exception} of emptyList
 * here return the response {@link HttpStatus} NOT_Found 
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ListEmptyException extends RuntimeException{


	private static Logger logger=LoggerFactory.getLogger(ListEmptyException.class);
	// this is default serialVersionUID
	private static final long serialVersionUID = 1L;
	public ListEmptyException(String errorMessage) {
		super(errorMessage);
		logger.warn("Inside class ListEmptyException!! constructor ListEmptyException!!");
	}
}
