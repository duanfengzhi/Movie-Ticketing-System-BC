package com.hgd.ebp.exception;

@SuppressWarnings("serial")
public class TicketException extends Exception{
	public TicketException(Exception e) {
		super(e);
	}
	
	public TicketException(String msg) {
		super(msg);
	}
}
