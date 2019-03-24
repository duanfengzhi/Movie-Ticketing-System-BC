package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class UserException extends Exception {
	public UserException(Exception e) {
		super(e);
	}
	
	public UserException(String msg) {
		super(msg);
	}
}
