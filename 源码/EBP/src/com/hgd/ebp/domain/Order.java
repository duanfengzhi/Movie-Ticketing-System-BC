package com.hgd.ebp.domain;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Order implements Serializable{
	private int oid;
	private Timestamp commitTime;
	private double amount;
	private int uid;
	
	public Order(){
		
	}
	
	public Order(int oid, Timestamp commitTime, double amount,int uid) {
		this.oid = oid; 
		this.commitTime = commitTime; 
		this.amount = amount; 
		this.uid = uid; 
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Timestamp getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Timestamp commitTime) {
		this.commitTime = commitTime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString(){ 
		return new String( oid+ " "+ commitTime+" "+  amount+" " + uid+" ");
	}
}
