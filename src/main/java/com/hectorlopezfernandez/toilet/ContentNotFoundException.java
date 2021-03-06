package com.hectorlopezfernandez.toilet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Represents a valid access to a missing resource, from a public web
 * perspective
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public final class ContentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7271205679977404347L;

	public ContentNotFoundException() {
		// No args constructor
	}

	public ContentNotFoundException(String message) {
		super(message);
	}

}
