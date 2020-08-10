package com.afarangoz.bookstore.api.common.exception.handler;

/**
 * The Class LoadFileException.
 */
public class LoadFileException extends RuntimeException{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new load file exception.
	 */
	public LoadFileException() {
		super();
	}

	/**
	 * Instantiates a new load file exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public LoadFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new load file exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public LoadFileException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new load file exception.
	 *
	 * @param message the message
	 */
	public LoadFileException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new load file exception.
	 *
	 * @param cause the cause
	 */
	public LoadFileException(Throwable cause) {
		super(cause);
	}
	

}
