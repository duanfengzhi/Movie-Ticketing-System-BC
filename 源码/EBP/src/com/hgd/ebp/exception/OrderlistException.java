package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class OrderlistException extends Exception {
	public OrderlistException(Exception e) {
		super(e);
	}
	
	public OrderlistException(String msg) {
		super(msg);
	}
}
