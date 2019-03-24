package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class AdminLoginException extends Exception {
	public AdminLoginException(Exception e) {
		super(e);
	}
	
	public AdminLoginException(String msg) {
		super(msg);
	}
}
