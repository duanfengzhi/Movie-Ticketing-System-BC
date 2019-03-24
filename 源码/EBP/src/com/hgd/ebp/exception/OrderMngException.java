package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class OrderMngException extends Exception {
	public OrderMngException(Exception e) {
		super(e);
	}
	
	public OrderMngException(String msg) {
		super(msg);
	}
}
