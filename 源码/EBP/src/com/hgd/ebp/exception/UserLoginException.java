package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class UserLoginException extends Exception{
	public UserLoginException(Exception e) {
		super(e);
	}
	
	public UserLoginException(String msg) {
		super(msg);
	}
}
