package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class AdminException extends Exception {
	public AdminException(Exception e) {
		super(e);
	}
	
	public AdminException(String msg) {
		super(msg);
	}
}
