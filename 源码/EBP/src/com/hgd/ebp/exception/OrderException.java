package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class OrderException extends Exception {
	public OrderException(Exception e) {
		super(e);
	}
	
	public OrderException(String msg) {
		super(msg);
	}
}
