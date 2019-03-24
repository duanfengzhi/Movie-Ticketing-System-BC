package com.hgd.ebp.domain;

import java.io.Serializable;
import java.sql.Timestamp;
@SuppressWarnings("serial")
public class Ticket implements Serializable{
	private int tid=0;
	private String descr=null;
	private Timestamp startTime=null;
	private int amount=0;
	private int balance=0;
	private double price=0;
	private int status=0;
	
	public Ticket(){
		
	}
	public Ticket(int tid,String descr,Timestamp startTime,int amount,int balance,double price,int status) {
		this.tid=tid;
		this.descr=descr;
		this.startTime=startTime;
		this.amount=amount;
		this.balance=balance;
		this.price=price;
		this.status=status;
	}
	
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid=tid;
	}

	public String getDescr() {
		return descr;
	}
	
	public void setDescr(String descr) {
		this.descr=descr;
	}
	
	public Timestamp getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Timestamp startTime) {
		this.startTime=startTime;
	}
	public int getAmount(){
		return amount;
	}
	public void setAmount(int amount){
		this.amount=amount;
	}
	public int getBalance(){
		return balance;
	}
	public void setBalance(int balance){
		this.balance=balance;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price=price;
	}
	
	public int getStatus(){
		return status;
	}
	public void setStatus(int status){
		this.status=status;
	}
	
	@Override
	public boolean equals(Object obj) {
        Ticket t=(Ticket)obj;
		return (this.tid==t.tid);
    }
	
}
