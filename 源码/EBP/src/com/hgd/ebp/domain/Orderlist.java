package com.hgd.ebp.domain;

public class Orderlist {
	private int lid;
	private String descr;
	private double price;
	private int quantity;
	private double amount;
	private int oid;
	
	public Orderlist(int lid, String descr, double price, int quantity,double amount,int oid) {
		this.lid = lid; 
		this.descr = descr; 
		this.price = price; 
		this.quantity = quantity; 
		this.amount = amount; 
		this.oid = oid; 
		
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuanity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}
	@Override
	public String toString(){ 
		return new String( lid+ " "+ descr+" "+  price+" " + quantity+" "+ amount+" "+ oid);
	}
}
