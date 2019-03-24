package com.hgd.ebp.domain;

import java.io.Serializable;
import java.sql.*;

@SuppressWarnings("serial")
public class  OrderMng implements Serializable{   //项目中的实体类Entity=domain
	private int uid;
	private int oid;
	private String descr;
	private Timestamp commitTime;
	private double amount;
	private String name;
	private String username;
	private String idCard;
	
	public OrderMng(){
		
	}
	
	public   OrderMng(int uid,int oid, String desc, Timestamp commitTime,double amount,String name,String username,String idCard) {
		this.oid = oid; 
		this.uid = uid; 
		this.descr=desc;
		this.commitTime=commitTime;
		this.amount=amount;
		this.name=name;
		this.username=username;
		this.idCard=idCard; 
	}
	
	public int getOid() {
		return oid;
	}

	public void setoid(int oid) {
		this.oid = oid;
	}
	
	public int getUid() {
		return uid;
	}

	public void setuid(int uid) {
		this.uid = uid;
	}

	public String getDescr() {
		return descr;
	}
	
	public void setDescr(String desc) {
		this.descr=desc;
	}
	
	public Timestamp getCommitTime() {
		return  commitTime;
	}

	public double getAmount() {
		return  amount;
	}
	
	public void setAmount(double amount) {
		this.amount=amount;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUserName(String username) {
		this.username=username;
	}
	
	public String getIdCard() {
		return idCard;
	}
	@Override public String toString(){ 
		String s=new String ( uid+" "+ oid+" "+descr+" "+commitTime+" "+amount+" "+name+" "+username+" "+idCard);
		return s;
	}
	
	public Object clone(){
		int u=uid;
		int o=oid;
		String d=descr;
		Timestamp cT=commitTime;
		double am=amount;
		String na=name;
		String usern=username;
		String iC=idCard;
		return new OrderMng(u,o,d,cT,am,na,usern,iC);
	}
}
	
	