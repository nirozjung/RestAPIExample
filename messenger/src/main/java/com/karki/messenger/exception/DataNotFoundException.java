package com.karki.messenger.exception;

import javax.ws.rs.ext.Provider;


public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2959857780016228316L;

	public DataNotFoundException(String message){
        super( message );
	}
}
