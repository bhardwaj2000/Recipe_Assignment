package com.recipe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@link RecipeNotFoundException} this class to handle custom {@link Exception} of Recipe not found in DB
 * here return the response {@link HttpStatus} NOT_Found 
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends RuntimeException {
	

	private static Logger logger=LoggerFactory.getLogger(RecipeNotFoundException.class);
	
	// this is default serialVersionUID
	private static final long serialVersionUID = 1L;

	public RecipeNotFoundException(String message) {
		super(message);
		logger.warn("InSide RecipeNotFoundException!! constructor RecipeNotFoundException !!");
	}

}
